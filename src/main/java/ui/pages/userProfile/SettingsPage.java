package ui.pages.userProfile;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ui.pages.MainPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SettingsPage {
    private final SelenideElement marketingNotifications = $(By.xpath("//a[contains(@class,'marketing-opt')]"));
    private final SelenideElement marketingNotificationsText = $(By.xpath("//a[contains(@class,'marketing-opt')]//span"));

    private final SelenideElement recommendations = $(By.xpath("//a[contains(@class,'suggestions')]"));
    private final SelenideElement recommendationsText = $(By.xpath("//a[contains(@class,'suggestions')]//span"));

    private final SelenideElement emailNotifications = $(By.xpath("//a[contains(@class,'change-email-freq')]"));
    private final SelenideElement emailNotificationClose = $(By.xpath("//a[contains(@class,'icon-close')]"));
    private final SelenideElement emailNotificationsSelect = $(By.xpath(
            "//ul[contains(@name,'minutesBetweenSummaries')]//span[contains(@class,'icon-check')]"));
    private final SelenideElement emailNotificationsMenu = $(By.xpath("//ul[contains(@name,'minutesBetweenSummaries')]//a"));

    //User info
    private final SelenideElement userInfoButton = $(By.xpath("//button[@data-test-id='header-member-menu-button']"));
    private final SelenideElement userInfoMenu = $(By.xpath("//section[@data-test-id='header-member-menu-popover']"));

    //Menu
    private final SelenideElement changeUserMenuProfile = $(By.xpath("//a[@data-test-id='header-member-menu-profile']"));
    private final SelenideElement returnToMainButton = $(By.xpath("//*[@aria-label='��������� �� ������� ��������']"));

    @Step("��������� ���������������� �������� ������������� ���������")
    public boolean changeMarketingNotification(){
        String[] marketingNotificationType = {"����������", "�����������"};
        String ifAgree = getTypeNotification(marketingNotificationsText);
        marketingNotifications.click();
        String ifAgreeNew = getTypeNotification(marketingNotificationsText);

        return ((ifAgree.equals(marketingNotificationType[0]) && ifAgreeNew.equals(marketingNotificationType[1])) ||
                (ifAgree.equals(marketingNotificationType[1]) && ifAgreeNew.equals(marketingNotificationType[0])))
                ? true : false;
    }

    @Step("��������� ���������������� ��������")
    public boolean changeRecommendation(){
        String[] recommendationNotificationType = {"��������", "���������"};
        String ifAgree = getTypeNotification(recommendationsText);
        recommendations.click();
        String ifAgreeNew = getTypeNotification(recommendationsText);

        return ((ifAgree.equals(recommendationNotificationType[0]) && ifAgreeNew.equals(recommendationNotificationType[1])) ||
                (ifAgree.equals(recommendationNotificationType[1]) && ifAgreeNew.equals(recommendationNotificationType[0])))
                ? true : false;
    }

    @Step("��������� ���������������� �������� ��������� �����")
    public String changeEmailNotificationType(String emailNotificationType){
        emailNotifications.click();
        emailNotificationsMenu.shouldHave(value(emailNotificationType)).click();
        emailNotifications.click();
        String value = emailNotificationsSelect.parent().getValue();
        emailNotificationClose.click();
        return value;
    }

    @Step("��������� ������� ��������")
    public String getTypeNotification(SelenideElement element){
        return element.getText().split(" ")[0];
    }

    @Step("��������� ���������������� ��������")
    public ProfilePage changeUserProfile() {
        //������� � ������
        try {
            userInfoButton.hover().click();
            userInfoMenu.shouldBe(enabled);
            changeUserMenuProfile.click();
        } catch (Exception e) {
            System.out.println("Changing user profile was failed. " + e.getMessage());
        }
        return Selenide.page(ProfilePage.class);
    }

    @Step("������� �� ������� ��������")
    public MainPage returnToMain() {
        try {
            returnToMainButton.hover().click();
        } catch (Exception e) {
            System.out.println("to return was not found. " + e.getMessage());
        }
        return page(MainPage.class);
    }
}
