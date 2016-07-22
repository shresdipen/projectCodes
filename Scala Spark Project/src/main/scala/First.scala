import org.apache.spark.SparkContext

import scala.collection.immutable.HashMap

/**
  * Created by dipen on 4/14/16.
  */
object First {
  def main(args: Array[String]) {

    val sc = new SparkContext( "local", "Word Count", "/usr/local/spark", Nil, Map(), Map())

    /* local = master URL; Word Count = application name; */
    /* /usr/local/spark = Spark Home; Nil = jars; Map = environment */
    /* Map = variables to work nodes */
    /*creating an inputRDD to read text file (in.txt) through Spark context*/
    val input = sc.textFile("/home/dipen/Desktop/word.txt")
    /* Transform the inputRDD into countRDD */

    val count = input.flatMap(line ⇒ line.split(";"))
      .map(word ⇒ (word, 1))
      .reduceByKey(_ + _)




    val field = List[String]("a", "b", "c", "d")
    val prep1 = input.flatMap(row ⇒ row.split("\n"))
      .map(row => (row.split(";").head, row))


    val prep2 = prep1
        .mapValues(x=>x.split(";")(2).toInt)
      .aggregateByKey(0)(_+_, _+_)
//        .reduceByKey(_+_)

//    val prep = prep1.distinct().join(prep2)



    for((k,v)<-prep2) {
      //      println(k + "->" + v + "sum: "+v._2)
      println(k + "->" + v)
    }
    /* saveAsTextFile method is an action that effects on the RDD */
//    count.saveAsTextFile("outfile")
    System.out.println("OK");
  }
}
