package main

import (
    "testing"
	"time"
)

func TestMonthDiff(test *testing.T) {
    
    checkDiff := func(t *testing.T, got, want int) {
        t.Helper()
        if got != want {
            test.Errorf("got %d want %d", got, want)
        }
    }
    
    test.Run("three months", func(t *testing.T) {
        start := time.Date(2012,time.Month(3),12, 0, 0, 0, 0, time.UTC)
        end := time.Date(2012,time.Month(6),10, 0, 0, 0, 0, time.UTC)
        got := monthDiff(start, end)
        checkDiff(t, got, 3)
    })

    test.Run("twelwe months", func(t *testing.T) {
        start := time.Date(2012,time.Month(3),12, 0, 0, 0, 0, time.UTC)
        end := time.Date(2013,time.Month(2),10, 0, 0, 0, 0, time.UTC)
        got := monthDiff(start, end)
        checkDiff(t, got, 11)
    })

    test.Run("twentyfour months", func(t *testing.T) {
        start := time.Date(2012,time.Month(3),12, 0, 0, 0, 0, time.UTC)
        end := time.Date(2014,time.Month(3),15, 0, 0, 0, 0, time.UTC)
        got := monthDiff(start, end)
        checkDiff(t, got, 24)
    })
}

func TestCalculateMonths(test *testing.T) {
    
    checkDiff := func(t *testing.T, got, want int) {
        t.Helper()
        if got != want {
            test.Errorf("got %d want %d", got, want)
        }
    }
    
    test.Run("first-one item", func(t *testing.T) {
        got := calculateMonths(&ALL_HISTORIES[0].JobList[0])
        checkDiff(t, got, 27)
    })
    test.Run("second-one item", func(t *testing.T) {
        got := calculateMonths(&ALL_HISTORIES[1].JobList[0])
        checkDiff(t, got, 73)
    })
    test.Run("third-one item", func(t *testing.T) {
        got := calculateMonths(&ALL_HISTORIES[2].JobList[0])
        checkDiff(t, got, 20)
    })
    test.Run("third-two item", func(t *testing.T) {
        got := calculateMonths(&ALL_HISTORIES[2].JobList[1])
        checkDiff(t, got, 4)
    })
}

func TestCalculateExperience(test *testing.T) {
    checkDiff := func(t *testing.T, got, want int) {
        t.Helper()
        if got != want {
            test.Errorf("got %d want %d", got, want)
        }
    }
    
    test.Run("third item", func(t *testing.T) {
        got := calculateExperience(&ALL_HISTORIES[2])
        checkDiff(t, got, 24)
    })
    test.Run("fourth item", func(t *testing.T) {
        got := calculateExperience(&ALL_HISTORIES[3])
        checkDiff(t, got, 74)
    })
}

func TestCalculateTotalExperience(test *testing.T) {
    got := calculateTotalExperience(ALL_HISTORIES)
    want := 340
    if got != want {
        test.Errorf("got %d want %d", got, want)
    }
}
