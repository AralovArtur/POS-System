package cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ee.ut.math.tvt.salessystem.dao.InMemorySalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import util.StockItemCreator;

public class StepDefinitionsStockItem {

    private static InMemorySalesSystemDAO dao = new InMemorySalesSystemDAO();
    private static ShoppingCart shoppingCart = new ShoppingCart(dao);
    private StockItem stockItem;
    private StockItem stockitemFromDB;

    @Given("^I have a empty stockitem$")
    public void iHaveAEmptyStockitem() {
        this.stockItem = new StockItem();
    }

    @When("^I create random stockitem$")
    public void iCreateRandomStockitem() {
        StockItemCreator stockItemCreator = new StockItemCreator();
        this.stockItem = stockItemCreator.create();
    }

    @And("^I add new stockitem to database$")
    public void iAddNewStockitemToDatabase(){
        dao.saveStockItem(stockItem);
    }

    @And("^I check added item in database$")
    public void iCheckAddedItemInDatabase() {
        stockitemFromDB = dao.findStockItem(stockItem.getId());
        assert stockitemFromDB != null;
    }

    @Then("^The returned stockitem should be correct$")
    public void theReturnedStockitemShouldBeCorrect() {
        this.stockitemFromDB = dao.findStockItem(stockItem.getId());
        assert this.stockItem.getId().equals(stockitemFromDB.getId());
        assert this.stockItem.getName().equals(stockitemFromDB.getName());
        assert this.stockItem.getDescription().equals(stockitemFromDB.getDescription());
        assert this.stockItem.getQuantity() == (stockitemFromDB.getQuantity());
        assert this.stockItem.getPrice() == stockitemFromDB.getPrice();
    }

    @And("^I change quantity of existing stockitem to \"([^\"]*)\"$")
    public void iChangeQuantityOfExistingStockitemToQuantity(int quantity) {
        StockItem checkingStockItemFromDB = dao.findStockItem(stockItem.getId());
        checkingStockItemFromDB.setQuantity(quantity);
    }

    @Then("^The returned stockitem quantity should be \"([^\"]*)\"$")
    public void theReturnedStockitemQuantityShouldBeQuantity(int quantity) {
        StockItem checkingStockItemFromDB = dao.findStockItem(stockItem.getId());
        assert checkingStockItemFromDB.getQuantity() == quantity;
    }

    @And("^I change price of existing stockitem to \"([^\"]*)\"$")
    public void iChangeQuantityOfExistingStockitemToNewPrice(double newPrice) {
        StockItem checkingStockItemFromDB = dao.findStockItem(stockItem.getId());
        checkingStockItemFromDB.setPrice(newPrice);

    }

    @Then("^The returned stockitem price should be \"([^\"]*)\"$")
    public void theReturnedStockitemPriceShouldBe(double newPrice){
        StockItem checkingStockItemFromDB = dao.findStockItem(stockItem.getId());
        assert checkingStockItemFromDB.getPrice() == newPrice;
    }
}
