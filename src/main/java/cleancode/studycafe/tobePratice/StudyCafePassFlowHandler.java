package cleancode.studycafe.tobePratice;

import cleancode.studycafe.tobePratice.io.InputHandler;
import cleancode.studycafe.tobePratice.io.OutputHandler;
import cleancode.studycafe.tobePratice.io.StudyCafeFileHandler;
import cleancode.studycafe.tobePratice.model.StudyCafeLockerPass;
import cleancode.studycafe.tobePratice.model.StudyCafePass;
import cleancode.studycafe.tobePratice.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassFlowHandler {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;


    public StudyCafePassFlowHandler(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }


    public void handleBasicPassSelection(StudyCafePassType passType) {
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        List<StudyCafePass> filteredPasses  = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                .toList();
        outputHandler.showPassListForSelection(filteredPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(filteredPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

    public void handleFixedPassWithLockerSelection(StudyCafePassType passType) {
        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
        List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
        List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
                .toList();
        outputHandler.showPassListForSelection(fixedPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
        StudyCafeLockerPass lockerPass = lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                                && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElse(null);

        boolean lockerSelection = false;
        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }
}
