
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer test1 = new LogAnalyzer();
        test1.readFile("short-test_log");
        test1.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer test1 = new LogAnalyzer();
        test1.readFile("weblog2_log");
        System.out.println("Unique IP count at "+test1.countUniqueIPs());
        test1.printAllHigherThanNum(400);
        System.out.println("---");
        System.out.println(test1.uniqueIPVisitsOnDay("Sep 27"));
        System.out.println(test1.uniqueIPVisitsOnDay("Sep 27").size());
        System.out.println(test1.countUniqueIPsInRange(400,499));
    }
    
    public void testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("-----countVisitsPerIP----");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        System.out.println("-----mostNumberVisitsByIP(counts)-----");
        int maxVisit = la.mostNumberVisitsByIP(counts);
        System.out.println(maxVisit);
        System.out.println("-----iPsMostVisits(counts)-----");
        System.out.println(la.iPsMostVisits(counts));
        System.out.println("-----iPsForDays()-----");
        HashMap<String,ArrayList<String>> ipDays = la.iPsForDays();
        System.out.println(ipDays);
        System.out.println("-----dayWithMostIPVisits(ipDays)-----");
        System.out.println(la.dayWithMostIPVisits(ipDays));
        System.out.println("-----iPsWithMostVisitsOnDay(ipDays,'Sep 30')-----");
        System.out.println(la.iPsWithMostVisitsOnDay(ipDays,"Sep 30"));
    }
}
