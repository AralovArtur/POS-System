import ee.ut.math.tvt.salessystem.dao.InMemorySalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.ShoppingCart;
import org.junit.Test;
import util.SoldItemCreator;
import util.StockItemCreator;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingCartTest {
    private static InMemorySalesSystemDAO dao = new InMemorySalesSystemDAO();
    private static ShoppingCart shoppingCart = new ShoppingCart(dao);

    @Test
    public void testAddingExistingItem() {
        shoppingCart.getAll().clear();
        StockItem stockItem = dao.findStockItem(4L);
        stockItem.setQuantity(100);
        SoldItem soldItem1 = new SoldItem(stockItem, 50);
        soldItem1.setId(stockItem.getId());
        SoldItem soldItem2 = new SoldItem(stockItem, 50);
        soldItem2.setId(stockItem.getId());
        shoppingCart.addItem(soldItem1);
        shoppingCart.addItem(soldItem2);
        List<SoldItem> soldItems = shoppingCart.getAll();
        int kontroll = 0;
        for (SoldItem item: soldItems) {
            if (item.getName() == soldItem1.getName())
                kontroll = item.getQuantity();
        }
        assertEquals(100, kontroll);
    }

    @Test
    public void testAddingNewItem() {
        shoppingCart.getAll().clear();
        StockItem stockItem = dao.findStockItem(4L);
        stockItem.setQuantity(100);
        SoldItem soldItem = new SoldItem(stockItem, 50);
        soldItem.setId(stockItem.getId());
        shoppingCart.addItem(soldItem);
        boolean isAdded = shoppingCart.getAll().contains(soldItem);
        assertEquals(true, isAdded);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithNegativeQuantity() {
        shoppingCart.getAll().clear();
        StockItem stockItem = dao.findStockItem(4L);
        stockItem.setQuantity(100);
        SoldItem soldItem = new SoldItem(stockItem, -50);
        shoppingCart.addItem(soldItem);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithQuantityTooLarge() {
        shoppingCart.getAll().clear();
        StockItem stockItem = dao.findStockItem(4L);
        stockItem.setQuantity(100);
        SoldItem soldItem = new SoldItem(stockItem, 105);
        soldItem.setId(stockItem.getId());
        shoppingCart.addItem(soldItem);
    }

    @Test(expected = NumberFormatException.class)
    public void testAddingItemWithQuantitySumTooLarge() {
        shoppingCart.getAll().clear();
        StockItem stockItem = dao.findStockItem(4L);
        stockItem.setQuantity(100);
        SoldItem soldItem1 = new SoldItem(stockItem, 50);
        soldItem1.setId(stockItem.getId());
        SoldItem soldItem2 = new SoldItem(stockItem, 55);
        soldItem2.setId(stockItem.getId());
        shoppingCart.addItem(soldItem1);
        shoppingCart.addItem(soldItem2);
    }

    @Test
    public void Checking_Shopping_Cart_After_Purchase(){
        shoppingCart.getAll().clear();
        StockItem stockItem = new StockItemCreator().create();
        dao.saveStockItem(stockItem);
        SoldItem soldItem = new SoldItemCreator(stockItem).create();
        shoppingCart.addItem(soldItem);
        shoppingCart.submitCurrentPurchase();
        assertTrue(shoppingCart.getAll().isEmpty());
    }


}
