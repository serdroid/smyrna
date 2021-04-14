package main

import (
//	"encoding/json"
	"time"
	"fmt"
	"golang.org/x/sys/unix"
)

type Person struct {
	Id   string `json:"id"`
	Name string `json:"name"`
	Surname       string `json:"surname"`
	Email     string `json:"email"`
}

type JobItem struct {
	Id   string `json:"id"`
	Position string `json:"position"`
	Start time.Time `json:"start"`
	End time.Time `json:"end"`
}

type JobHistory struct {
	PersonId   string `json:"personId"`
	JobList    []JobItem  `json:"jobList"`
}

var ALL_HISTORIES = []JobHistory {
    JobHistory {PersonId:"123", JobList: []JobItem{
        {Id:"1543", Position: "Software Engineer", Start:time.Date(2012,time.Month(3),12, 0, 0, 0, 0, time.UTC), End:time.Date(2014,time.Month(6),21, 0, 0, 0, 0, time.UTC)},
        {Id:"1544", Position: "Software Engineer", Start:time.Date(2014,time.Month(7),1, 0, 0, 0, 0, time.UTC), End:time.Date(2021,time.Month(4),15, 0, 0, 0, 0, time.UTC)},
    }},
    JobHistory {PersonId:"124", JobList: []JobItem{
        {Id:"1545", Position: "Devops Engineer", Start:time.Date(2010,time.Month(2),2, 0, 0, 0, 0, time.UTC), End:time.Date(2016,time.Month(3),1, 0, 0, 0, 0, time.UTC)},
        {Id:"1546", Position: "Senior Devops Engineer", Start:time.Date(2016,time.Month(3),10, 0, 0, 0, 0, time.UTC), End:time.Date(2021,time.Month(4),15, 0, 0, 0, 0, time.UTC)},
    }},
    JobHistory {PersonId:"125", JobList: []JobItem{
        {Id:"1547", Position: "UI Designer", Start:time.Date(2019,time.Month(2),2, 0, 0, 0, 0, time.UTC), End:time.Date(2020,time.Month(10),30, 0, 0, 0, 0, time.UTC)},
        {Id:"1548", Position: "UI Designer", Start:time.Date(2020,time.Month(11),1, 0, 0, 0, 0, time.UTC), End:time.Date(2021,time.Month(3),30, 0, 0, 0, 0, time.UTC)},
    }},
    JobHistory {PersonId:"126", JobList: []JobItem{
        {Id:"1549", Position: "Recruiter", Start:time.Date(2015,time.Month(1),2, 0, 0, 0, 0, time.UTC), End:time.Date(2020,time.Month(1),5, 0, 0, 0, 0, time.UTC)},
        {Id:"1550", Position: "Lead Recruiter", Start:time.Date(2020,time.Month(1),6, 0, 0, 0, 0, time.UTC), End:time.Date(2021,time.Month(3),31, 0, 0, 0, 0, time.UTC)},
    }},
}

func calculateTotalExperience(histories []JobHistory) int {
    nOfThreads := 4
    chint := make(chan int, nOfThreads)
    for idx, history := range(histories) {
        go calculateExperienceAsync(idx, history, chint)
    }
    totalExp := 0
    for idx := 0; idx < len(histories); idx++ {
        totalExp += <- chint
    }    
    return totalExp    
}

func calculateExperienceAsync(idx int, history JobHistory, chint chan int) {
    fmt.Printf("worker %d is calculating experience of %q in %x \n", idx, history.PersonId, unix.Gettid() )
    total := calculateExperience(&history)
    chint <- total
}


func calculateExperience(history *JobHistory) int {
    var exp = 0
    for _, item := range(history.JobList) {
        exp += calculateMonths(&item)
    }
    return exp
}

func calculateMonths(item *JobItem) int {
    end := item.End
    if end.IsZero() {
        end = time.Now()
    }
    return monthDiff(item.Start, end)    
}

func monthDiff( start, end time.Time) int {
    return ( end.Year() - start.Year() ) * 12 + int(end.Month()) - int(start.Month())
}
