/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curso.springboot.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Robison
 */
public class URL {
    
    public static List<Integer> decodeIntList(String s){
        /*String[] stringArray = s.split(",");
        List<Integer> list = new ArrayList<>();
        
        for(String string : stringArray){
            list.add(Integer.parseInt(string));
        }
        
        return list;*/
        return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
    
    public static String decodeParam(String s){
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return "";
            //Logger.getLogger(URL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
