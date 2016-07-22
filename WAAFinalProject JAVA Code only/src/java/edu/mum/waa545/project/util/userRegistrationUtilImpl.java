/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa545.project.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author tsegaab
 */
@Component
public class userRegistrationUtilImpl implements userRegistrationUtil {

    private List<String> countryList = new ArrayList();

    public userRegistrationUtilImpl() {
        countryList.add("Country1");
        countryList.add("country2");
        countryList.add("country3");
        countryList.add("country4");
        countryList.add("country5");
        countryList.add("country6");
        countryList.add("country7");
        countryList.add("country8");
        countryList.add("country9");
        countryList.add("country10");
        countryList.add("country11");
        countryList.add("country12");
        countryList.add("country13");

    }

    @Override
    public List<String> getAllCountries() {
        return countryList;
    }

}
