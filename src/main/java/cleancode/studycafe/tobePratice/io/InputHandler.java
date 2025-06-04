package cleancode.studycafe.tobePratice.io;

import cleancode.studycafe.tobePratice.exception.AppException;
import cleancode.studycafe.tobePratice.model.StudyCafePass;
import cleancode.studycafe.tobePratice.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String INPUT_HOURLY = "1";
    private static final String INPUT_WEEKLY = "2";
    private static final String INPUT_FIXED = "3";

    private static final String INPUT_LOCKER = "1";


    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = SCANNER.nextLine();

        if (INPUT_HOURLY.equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if (INPUT_WEEKLY.equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if (INPUT_FIXED.equals(userInput)) {
            return StudyCafePassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
        String userInput = SCANNER.nextLine();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    public boolean getLockerSelection() {
        String userInput = SCANNER.nextLine();
        return INPUT_LOCKER.equals(userInput);
    }

}
