package org.example.util;

import org.example.entity.Lesson;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class EarliestLesson {

    public Long defineIdToEarliestLesson(List<Lesson> lessons){

        long minTimeToLesson = lessons.get(0).getDate().atZone(ZoneId.of("Europe/Kiev")).toInstant().toEpochMilli();
        long idOfMinTimeToLesson = lessons.get(0).getId();
        long currentTimeOfLesson;

        for(Lesson lesson:lessons){
            if((lesson.getDate().atZone(ZoneId.of("Europe/Kiev")).toInstant().toEpochMilli() - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()) >= 0){
                currentTimeOfLesson = lesson.getDate().atZone(ZoneId.of("Europe/Kiev")).toInstant().toEpochMilli();
                if(currentTimeOfLesson < minTimeToLesson){
                    minTimeToLesson = currentTimeOfLesson;
                    idOfMinTimeToLesson = lesson.getId();
                }
            }
        }

        return idOfMinTimeToLesson;
    }
}
