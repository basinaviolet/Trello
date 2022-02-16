package uiTests.smokeTests;

import com.codeborne.selenide.junit5.ScreenShooterExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.extension.ExtendWith;
import uiTests.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ui.pages.MainPage;
import ui.pages.userProfile.SettingsPage;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(ScreenShooterExtension.class)
public class UserInfoSettingsChangeTest extends BaseTest {

    private final MainPage mainPage = new MainPage();
    private final SettingsPage settingsPage = mainPage.changeUserSettings();

    @Epic(value = "�������� ������������")
    @Feature(value = "�������� ����������� ������������")
    @Story(value = "��������� �������� ��������� ������������� ���������")
    @Description("�������� ����������� ��������� �������� ��������� ������������� ��������� (�������� ��� �� ��������)")
    @Severity(SeverityLevel.MINOR)
    @RepeatedTest(2)
    @Owner(value = "Violetta Basina")
    public void changeUserInfoNotification() {
        Assertions.assertAll("User profile",
                () -> Assertions.assertTrue(settingsPage.changeMarketingNotification()),
                () -> Assertions.assertTrue(settingsPage.changeRecommendation())
        );
    }

    @Epic(value = "�������� ������������")
    @Feature(value = "�������� ����������� ������������")
    @Story(value = "��������� ������� ��������� �������������� e-mail ���������")
    @Description("�������� ����������� ��������� ������� ��������� ��������������  e-mail ��������� (�������, ���������, ���� ��� � ���)")
    @Severity(SeverityLevel.MINOR)
    @ParameterizedTest
    @Owner(value = "Violetta Basina")
    @CsvSource({"�������"})
    public void changeEmailNotificationType(String emailNotificationInput) {
        Map<String, String> emailNotificationType = new HashMap<>();
        {
            emailNotificationType.put("�������", "-1");
            emailNotificationType.put("������������", "60");
            emailNotificationType.put("���������", "1");
        }

        Assertions.assertEquals(emailNotificationType.get(emailNotificationInput),
                settingsPage.changeEmailNotificationType(emailNotificationType.get(emailNotificationInput)));
    }
}

