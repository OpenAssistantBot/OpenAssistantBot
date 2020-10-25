package com.github.openassistant.service.impl;

import com.github.openassistant.model.Step;
import com.github.openassistant.repository.QuestionRepository;
import com.github.openassistant.model.Question;
import com.github.openassistant.service.CsvParserService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CsvParserServiceImpl implements CsvParserService {

    private final ResourceLoader resourceLoader;
    private final static String sourcePath = "./data/knowledge_base.xlsx";

    private final QuestionRepository questionRepository;

    @Override
    public List<String> initDatabase() {
        List<Question> questions = null;
        try {
            questions = parseCsv(loadData().getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionRepository.saveAll(questions);
        return questions.stream().map(Question::getQuestion).collect(Collectors.toList());
    }

    @Override
    public List<String> updateDatabase(String path) {
        List<Question> questions = parseCsv(path);
        questionRepository.saveAll(questions);
        return questions.stream().map(Question::getQuestion).collect(Collectors.toList()); // не учитывает дубликаты
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
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
                        fillQuestion(question, links, steps, cell);
                    }
                }

                if (question.getQuestion() != null && !steps.isEmpty()) {
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

    private void fillQuestion(Question question, List<String> links, List<Step> steps, Cell cell) {
        switch (cell.getColumnIndex()) {
            case 0:
                question.setNumber((long) cell.getNumericCellValue());
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

    public Resource loadData() {
        return resourceLoader.getResource(
                "classpath:data/knowledge_base.xlsx");
    }
}
