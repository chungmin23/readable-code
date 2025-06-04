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

    private static final String LOCKER_PATH = "src/main/resources/cleancode/studycafe/locker.csv";
    private static final String CSV_SEPARATOR = ",";
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_DURATION = 1;
    private static final int INDEX_PRICE = 2;
    private static final int INDEX_DISCOUNT = 3;

    public List<StudyCafePass> readStudyCafePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(PASS_CSV_PATH));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(CSV_SEPARATOR);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[INDEX_TYPE]);
                int duration = Integer.parseInt(values[INDEX_DURATION]);
                int price = Integer.parseInt(values[INDEX_PRICE]);
                double discountRate = Double.parseDouble(values[INDEX_DISCOUNT]);

                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return studyCafePasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }

    public List<StudyCafeLockerPass> readLockerPasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOCKER_PATH));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(CSV_SEPARATOR);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[INDEX_TYPE]);
                int duration = Integer.parseInt(values[INDEX_DURATION]);
                int price = Integer.parseInt(values[INDEX_PRICE]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return lockerPasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }



}
