package ui.pages.logination;

import io.qameta.allure.Step;
import ui.pages.MainPage;

import static com.codeborne.selenide.Selenide.page;

public class AtlassianAddPage {

    @Step("������� �� ������ ��������")
    public MainPage getMainPage() {
        return page(MainPage.class);
    }

    @Step("������� �� ������ ��������")
    public StartPage getStartPage() {
        return page(StartPage.class);
    }

}
