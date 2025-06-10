package cleancode;


import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;


public class StudyCafeTest {

    @Test
    @DisplayName("2주권이상인 경우 이상 10% 할인이 된다 ")
    void weeklyDiscount() {
        //given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 26000, 0.1);

        //then
        assertEquals(2600, pass.getDiscountPrice());
    }

    @Test
    @DisplayName("12주권 이상인 경우 15% 할인이 된다")
    void twelveWeekDiscount() {
        //given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 78000, 0.15);

        //then
        assertEquals(11700, pass.getDiscountPrice());
    }
//
//
    @Test
    @DisplayName("주 단위 이용권은 사물함을 구매할 수 없다 ")
    void weeklyPassCannotUseLocker() {
        //given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 52000, 0.1);
        //then
        assertTrue(pass.cannotUseLocker());
    }
//
//
    @Test
    @DisplayName("시간 단위 이용권은 사물함을 구매할 수 없다 ")
    void hourlyPassCannotUseLocker() {
        //given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 8000, 0.0);
        //then
        assertTrue(pass.cannotUseLocker());
    }

    @Test
    @DisplayName("사용자가 제시된 이용권 외 다른 번호를 선택하면 예외를 던진다")
    void inputOutOfRangeThrowsException() {
        // given
        System.setIn(new ByteArrayInputStream("4".getBytes()));
        InputHandler inputHandler = new InputHandler();

        // when & then
        assertThrows(AppException.class, inputHandler::getPassTypeSelectingUserAction);
    }

    @Test
    @DisplayName("4시간 이용권 자유석 이용을 하여 6500원 결제하였다")
    void fourHourPassTotalPrice() {
        //gien
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 6500, 0.0);
        StudyCafePassOrder order = StudyCafePassOrder.of(pass, null);

        // when & then
        assertEquals(6500, order.getTotalPrice());
    }

    @Test
    @DisplayName("1인 고정석 4주권, 사물함 이용하지 않음")
    void fixedPassWithoutLocker() {
        //given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 52000, 0.1); // 할인 5200
        StudyCafePassOrder order = StudyCafePassOrder.of(pass, null);

        // when & then
        assertEquals(52000 - 5200, order.getTotalPrice());
        assertTrue(order.getLockerPass().isEmpty());
    }
}
