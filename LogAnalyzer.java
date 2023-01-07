
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
     ArrayList<String> uniqueIPs = new ArrayList<String>();
     for (LogEntry le: records) {
         String ipAddr = le.getIpAddress();
         if (!uniqueIPs.contains(ipAddr)) {
             uniqueIPs.add(ipAddr);
         }
     }
     return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         for (LogEntry le: records) {
             int scode = le.getStatusCode();
             if (scode > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String day) {
         ArrayList<String> UIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             String logdate = le.getAccessTime().toString();
             if(logdate.contains(day)) {
                 String IPnow = le.getIpAddress();
                 if (!UIPs.contains(IPnow)) {
                     UIPs.add(IPnow);
                }
             }
         }
     return UIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> unique_ip = new ArrayList<String>();
         for (LogEntry le: records) {
             int scode = le.getStatusCode();
             if ((scode >= low) && (scode <= high)) {
                 String ipAddr = le.getIpAddress();
                 if (!unique_ip.contains(ipAddr)) {
                    unique_ip.add(ipAddr);
                    }
             }
            }
         System.out.println("Unique IPs: "+unique_ip);
         return unique_ip.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (!counts.containsKey(ip)) {
                counts.put(ip,1);
            }
            else {
                counts.put(ip,counts.get(ip)+1);
            }
        }
        System.out.println("Size of unique IPs: "+counts.size());
        return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int max = 0;
        String ip="";
        for(String elts : map.keySet()) {
            if (map.get(elts) > max) {
                max = map.get(elts);
                ip = elts;
            }
        }
        return max;
        }
        
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
         ArrayList<String> result = new ArrayList<String>();
         int max = mostNumberVisitsByIP(map);
         for(String elts : map.keySet()) {
             if (map.get(elts) == max) {
                result.add(elts);
                }
            }
         return result;
        }
        
     public HashMap<String,ArrayList<String>> iPsForDays() {
        HashMap<String,ArrayList<String>> result = new HashMap<String,ArrayList<String>>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            String date = le.getAccessTime().toString();
            date = date.substring(4,10);
            ArrayList<String> ipArray = new ArrayList<String>();
            if(!result.containsKey(date)) {
                ipArray.add(ip);
                result.put(date,ipArray);
            }
            else {
                 //if(!result.get(date).contains(ip)) {
                    result.get(date).add(ip);
                 //   }
            }
        }
        return result;
        }
        
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map) {
        String result = "";
        int max = 0;
            for(String key : map.keySet()) {
                if(map.get(key).size() > max) {
                    max = map.get(key).size();
                    result = key;
                }
            }
        return result;
        }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String day) {
        ArrayList<String> result = new ArrayList<String>();
        HashMap<String,Integer> rez = new HashMap<String,Integer>();
        for (String elt : map.get(day)) {
            if(rez.containsKey(elt)) {
                int temp = rez.get(elt) + 1;
                rez.put(elt,temp);
            }
            else {
                rez.put(elt,1);
            }
        }
        result = iPsMostVisits(rez);
        return result;
        }
}
