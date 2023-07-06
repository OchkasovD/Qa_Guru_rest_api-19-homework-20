package tests;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static helpers.AuthApi.authCookieKey;
import static io.restassured.RestAssured.given;


public class EditAccountTests extends TestBase {
    String authCookieValue;

    @Test
    void editAddressTest() {

        step("Check name title", () -> {
            open("/login");
            $("#Email").setValue(login);
            $("#Password").setValue(password).pressEnter();
            open("/login");
            open("/customer/addresses");
            $(".info").shouldHave(text("Rulon Oboev"));
        });

        step("Get authCookieValue", () -> {
            authCookieValue = authApi.getAuthCookie(login, password);
        });

        step("Edit name and address  by API", () -> {
            Map<String, String> data = new HashMap<>();
            data.put("Address.Id", "3147862");
            data.put("Address.FirstName", "Ivan");
            data.put("Address.LastName", "Ivanov");
            data.put("Address.Email", "test@gmail.com");
            data.put("Address.Company", "company");
            data.put("Address.CountryId", "2");
            data.put("Address.StateProvinceId", "63");
            data.put("Address.City", "city");
            data.put("Address.Address1", "address1");
            data.put("Address.Address2", "address2");
            data.put("Address.ZipPostalCode", "123456");
            data.put("Address.PhoneNumber", "098987876");
            data.put("Address.FaxNumber", "112233");

            given()
                    .contentType("application/x-www-form-urlencoded")
                    .cookie(authCookieKey, authCookieValue)
                    .formParams(data)
                    .when()
                    .post("/customer/addressedit/3147862")
                    .then()
                    .log().all()
                    .statusCode(302);
        });

        step("Check edited name title", () -> {
            open("/login");
            $("#Email").setValue(login);
            $("#Password").setValue(password).pressEnter();
            open("/login");
            open("/customer/addresses");
            $(".info").shouldHave(text("Ivan Ivanov"));
        });

        step("Rollback by API", () -> {
            Map<String, String> initial = new HashMap<>();
            initial.put("Address.Id", "3147862");
            initial.put("Address.FirstName", "Rulon");
            initial.put("Address.LastName", "Oboev");
            initial.put("Address.Email", "test@gmail.com");
            initial.put("Address.Company", "company");
            initial.put("Address.CountryId", "2");
            initial.put("Address.StateProvinceId", "63");
            initial.put("Address.City", "city");
            initial.put("Address.Address1", "address1");
            initial.put("Address.Address2", "address2");
            initial.put("Address.ZipPostalCode", "123456");
            initial.put("Address.PhoneNumber", "098987876");
            given()
                    .contentType("application/x-www-form-urlencoded")
                    .cookie(authCookieKey, authCookieValue)
                    .formParams(initial)
                    .when()
                    .post("/customer/addressedit/3147862")
                    .then()
                    .log().all()
                    .statusCode(302);
        });
    }
}




