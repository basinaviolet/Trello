package uiTests.regressionTests;

import io.qameta.allure.*;
import uiTests.BaseTest;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ui.pages.BoardPage;
import ui.pages.MainPage;

import java.util.stream.Stream;

@ExtendWith(ScreenShooterExtension.class)
public class BoardCreateNewTest extends BaseTest {

    private final String mainPageCheck = "���� ������� ������������";
    private MainPage mainPage = new MainPage();

    static Stream<Arguments> dataForCheckOfNewBoardCreation() {
        return Stream.of(
                Arguments.of("MyBoardWithPictures", "pictures", "0"),
                Arguments.of("MyBlueBoard", "color", "�����"),
                Arguments.of("MyOrangeBoard", "color", "���������"),
                Arguments.of("MyGreenBoard", "color", "������"),
                Arguments.of("MyRedBoard", "color", "�������"),
                Arguments.of("MyVioletBoard", "color", "����������"),
                Arguments.of("MyColoredBoard", "color", "0")//color = 0 -> random selection
        );
    }

    @Epic(value = "�������� ������������")
    @Feature(value = "�������� � �������")
    @Story(value = "�������� ����� ����� � �������� ������ � �����")
    @Description("�������� ����� ����� � ������� ����� ��� ����� � ���� ��������� �������� �� ������")
    @Severity(SeverityLevel.NORMAL)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @MethodSource("dataForCheckOfNewBoardCreation")
    public void testNewBoardCreation(String boardName, String boardViewType, String color) {
        String boardMenuItem = "�����";
     //   MainPage mainPage = new MainPage();
        int startBoardCount = mainPage.leftMenuPointClick(boardMenuItem).getBoardCount();

        // create new board
        BoardPage boardPage = mainPage
                .createNewBoard(boardName, boardViewType, color);
        Assertions.assertTrue(boardPage.ifBoardPage(boardName), "The board was not created");

        //if return to main page check
        boardPage.returnToMain();
        Assertions.assertTrue(mainPage.ifMainPage(), "The main page was not found");

        // new count of boards on the main paige
        int endBoardCount = mainPage.leftMenuPointClick(boardMenuItem).getBoardCount();
        Assertions.assertEquals(1, (endBoardCount - startBoardCount), "Count of boards on the main page does not match");
    }

    @Epic(value = "�������� ������������")
    @Feature(value = "�������� � �������")
    @Story(value = "�������� ����� ����� �� �������")
    @Description("�������� ����� ����� �� ��������� ������� � �������� ���������")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @Owner(value = "Violetta Basina")
    public void testNewTemplateBoardCreation() {
        String boardName = "MyTemplateBoard";
   ///     MainPage mainPage = new MainPage();
        BoardPage boardPage = mainPage
                .createNewTemplateBoard(boardName);

        Assertions.assertTrue(boardPage.ifTemplateBoardPage(boardName), "The board was not created");

        mainPage = boardPage.returnToMain();
        Assertions.assertTrue(mainPage.ifMainPage(), "The main page was not found");
    }
}
