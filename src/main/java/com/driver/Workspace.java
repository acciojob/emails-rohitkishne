package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.

        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar

        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am

        int calendarSize = calendar.size();

        if(calendarSize==0)
        {
            return 0;
        }

        Collections.sort(calendar, new Comparator<Meeting>(){

            @Override
            public int compare(Meeting m1, Meeting m2) {
                return m1.getEndTime().compareTo(m2.getEndTime());
            }
        });

        int cnt = 1;
        int j=0;
        for(int i=1; i<calendarSize; i++)
        {
            if(calendar.get(i).getStartTime().compareTo(calendar.get(j).getEndTime())>0) {
                cnt++;
                j = i;
            }
        }

        return cnt;
    }
}
