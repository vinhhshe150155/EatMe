package com.example.areal.Models;

import java.util.ArrayList;




public class RecipeNutriResponse {
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */



        public String calories;
        public String carbs;
        public String fat;
        public String protein;
        public ArrayList<Bad> bad;
        public ArrayList<Good> good;



}
