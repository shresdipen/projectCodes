import org.apache.spark.SparkContext

/**
  * Created by dipen on 4/14/16.
  */
object ExpenseAnalyzer {

  //ID;Name;Type;Detail;Amount;Date(yyyy-MM-dd)
  def main(args: Array[String]) {

    val sc = new SparkContext( "local", "Word Count", "/usr/local/spark", Nil, Map(), Map())

    val input = sc.textFile("src/main/expense.txt")
    /* Transform the inputRDD into countRDD */

    val customerDetail = input.flatMap(row=> row.split("/n"))
                                .map(row=>(row.split(";")(0), row.split(";")(1))).distinct()

    val expenseMonthly = customerDetail.join(input.flatMap(row=> row.split("/n"))
                          .map(row=> (row.split(";")(0)+"-_-"+row.split(";")(2)+"-_-"
                            +row.split(";")(5).substring(0,7)
                            ,row))
                            .mapValues(value => value.split(";")(4).toDouble)
                            .reduceByKey(_+_)
                            .map(row=>(row._1.split("-_-")(0),
                              row._1.split("-_-")(1)+","+row._1.split("-_-")(2)
                                +","+row._2)))
      .sortBy(row=>(row._1, row._2._2.split(",")(1), row._2._2.split(",")(0)))


    val expenseQuaterly = customerDetail.join(input.flatMap(row=> row.split("/n"))
      .map(row=> (row.split(";")(0)+"-_-"+row.split(";")(2)+"-_-"
        +getQuarter(row.split(";")(5))
        ,row))
      .mapValues(value => value.split(";")(4).toDouble)
      .reduceByKey(_+_)
      .map(row=>(row._1.split("-_-")(0),
        row._1.split("-_-")(1)+","+row._1.split("-_-")(2)
          +","+row._2)))
      .sortBy(row=>(row._1, row._2._2.split(",")(1), row._2._2.split(",")(0)))

    val expenseYearly = customerDetail.join(input.flatMap(row=> row.split("/n"))
                  .map(row=> (row.split(";")(0)+"-_-"+row.split(";")(2)+"-_-"
                    +row.split(";")(5).substring(0,4)
                    ,row))
                  .mapValues(value => value.split(";")(4).toDouble)
                  .reduceByKey(_+_)
                  .map(row=>(row._1.split("-_-")(0),
                    row._1.split("-_-")(1)+","+row._1.split("-_-")(2)
                      +","+row._2)))
                  .sortBy(row=>(row._1, row._2._2.split(",")(1), row._2._2.split(",")(0)))


//    expenseMonthly.foreach(println)
//
//    expenseQuaterly.foreach(println)

    expenseMonthly.saveAsTextFile("outputMonthly")
    expenseQuaterly.saveAsTextFile("outputQuaterly")
    expenseYearly.saveAsTextFile("outputYearly")
    println("OK")
  }

  def getQuarter(date:String):String={
    val month = date.substring(5,7).toInt
    if(month<4) return date.substring(0,5)+"01"
    if(month<7)return date.substring(0,5)+"02"
    if(month<10)return date.substring(0,5)+"03"
    return date.substring(0,5)+"04"
  }
}
