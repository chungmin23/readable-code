package cleancode.studycafe.tobePratice;

import cleancode.studycafe.tobePratice.exception.AppException;
import cleancode.studycafe.tobePratice.io.InputHandler;
import cleancode.studycafe.tobePratice.io.OutputHandler;
import cleancode.studycafe.tobePratice.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();


    public void run() {
        try {
            // 출력단 통합
            outputHandler.showTotalPrint();

            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            StudyCafePassFlowHandler studyCafePassFlowHandler = new StudyCafePassFlowHandler(inputHandler, outputHandler);

            if (studyCafePassType == StudyCafePassType.HOURLY) {

                studyCafePassFlowHandler.handleBasicPassSelection(StudyCafePassType.HOURLY);

            } else if (studyCafePassType == StudyCafePassType.WEEKLY) {

                studyCafePassFlowHandler.handleBasicPassSelection(StudyCafePassType.WEEKLY);

            } else if (studyCafePassType == StudyCafePassType.FIXED) {

                studyCafePassFlowHandler.handleFixedPassWithLockerSelection(StudyCafePassType.FIXED);

            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
