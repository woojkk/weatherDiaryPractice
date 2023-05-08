package zerobase.weatherdiarypractice.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weatherdiarypractice.domain.Diary;
import zerobase.weatherdiarypractice.serivce.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation("일기 저장")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "저장할 날짜", example = "2020-02-02")
                     LocalDate date,
                     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택 날짜의 일기 조회")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "조회할 날짜", example = "2020-02-02")
                          LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택 기간중의 모든 일기 조회")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회할 기간의 첫번째 날", example = "2020-02-02")
            LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회할 기간의 마지막 날", example = "2020-02-02")
            LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택 날짜의 일기 수정")
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "수정할 날짜", example = "2020-02-02")
            LocalDate date,
            @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택 날짜의 일기 삭제")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "삭제할 날짜", example = "2020-02-02") LocalDate date
    ) {
        diaryService.deleteDiary(date);
    }
}
