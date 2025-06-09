package cleancode.studycafe.tobePratice.io;

import cleancode.studycafe.tobePratice.model.StudyCafeLockerPass;
import cleancode.studycafe.tobePratice.model.StudyCafePass;
import cleancode.studycafe.tobePratice.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeFileHandler {

    private static final String PASS_CSV_PATH = "src/main/resources/cleancode/studycafe/pass-list.csv";

    private static final String LOCKER_CSV_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    private static final String CSV_SEPARATOR = ",";
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_DURATION = 1;
    private static final int INDEX_PRICE = 2;
    private static final int INDEX_DISCOUNT = 3;

    public List<StudyCafePass> readStudyCafePasses() {
        List<String> lines = readLines(PASS_CSV_PATH);
        List<StudyCafePass> passes = new ArrayList<>();
        for (String line : lines) {
            passes.add(parsePass(line));
        }
        return passes;
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        List<String> lines = readLines(LOCKER_CSV_PATH);
        List<StudyCafeLockerPass> passes = new ArrayList<>();
        for (String line : lines) {
            passes.add(parseLockerPass(line));
        }
        return passes;
    }

    private List<String> readLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 실패: " + path, e);
        }
    }

    private StudyCafePass parsePass(String line) {
        String[] values = line.split(CSV_SEPARATOR);
        StudyCafePassType type = StudyCafePassType.valueOf(values[INDEX_TYPE]);
        int duration = Integer.parseInt(values[INDEX_DURATION]);
        int price = Integer.parseInt(values[INDEX_PRICE]);
        double discount = Double.parseDouble(values[INDEX_DISCOUNT]);
        return StudyCafePass.of(type, duration, price, discount);
    }

    private StudyCafeLockerPass parseLockerPass(String line) {
        String[] values = line.split(CSV_SEPARATOR);
        StudyCafePassType type = StudyCafePassType.valueOf(values[INDEX_TYPE]);
        int duration = Integer.parseInt(values[INDEX_DURATION]);
        int price = Integer.parseInt(values[INDEX_PRICE]);
        return StudyCafeLockerPass.of(type, duration, price);
    }





}
