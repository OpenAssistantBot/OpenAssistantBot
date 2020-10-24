package com.github.openboot.service.impl;

import com.github.openboot.model.Question;
import com.github.openboot.model.Step;
import com.github.openboot.repository.QuestionRepository;
import com.github.openboot.service.CsvParserService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CsvParserServiceImpl implements CsvParserService {

    private final static String sourcePath = "data/knowledge_base.xlsx";

    private final QuestionRepository questionRepository;

    @Override
    public List<String> initDB() {
        List<Question> questions = parseCsv(sourcePath);
        questionRepository.saveAll(questions);
        return questions.stream().map(Question::getQuestion).collect(Collectors.toList());
    }

    @Override
    public List<String> updateDB(String path) {
        List<Question> questions = parseCsv(path);
        questionRepository.saveAll(questions);
        return questions.stream().map(Question::getQuestion).collect(Collectors.toList()); // не учитывает дубликаты
    }

    private List<Question> parseCsv(String file) {
        List<Question> questions = new ArrayList<>();
        try {
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
            Question question;
            List<String> links;
            List<Step> steps;
            for (Row cells : myExcelSheet) {
                question = new Question();
                links = new ArrayList<>();
                steps = new ArrayList<>();
                for (Cell cell : cells) {
                    if (!cell.toString().isEmpty() && cells.getRowNum() != 0) {
                        switch (cell.getColumnIndex()) {
                            case 0:
                                question.setId((long) cell.getNumericCellValue());
                                break;
                            case 1:
                                question.setQuery(cell.getStringCellValue());
                                break;
                            case 2:
                                question.setClarification(cell.getStringCellValue());
                                break;
                            case 3:
                                question.setType(cell.getStringCellValue());
                                break;
                            case 4:
                                question.setQuestion(cell.getStringCellValue());
                                break;
                            case 5:
                            case 6:
                            case 7:
                                links.add(cell.getStringCellValue());
                                break;
                            default: {
                                Step step = new Step();
                                step.setValue(cell.getStringCellValue());
                                steps.add(step);
                            }
                        }
                    }
                }
                if (question.getQuestion() != null) {
                    question.setSteps(steps);
                    question.setLinks(links);
                    questions.add(question);
                }
            }
            myExcelBook.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return questions;
    }
}
