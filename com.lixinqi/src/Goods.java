@SuppressWarnings({"all"})
public class Goods {
    private int GoodsId;
    private String GoodsCode;
    private String GoodName;
    private double GoodsPrice;
    private int GoodsNum;

    public Goods(int goodsId, String goodsCode, String goodName, double goodsPrice, int goodsNum) {
        GoodsId = goodsId;
        GoodsCode = goodsCode;
        GoodName = goodName;
        GoodsPrice = goodsPrice;
        GoodsNum = goodsNum;
    }

    public int getGoodsId() {
        return GoodsId;
    }

    public void setGoodsId(int goodsId) {
        GoodsId = goodsId;
    }

    public String getGoodsCode() {
        return GoodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        GoodsCode = goodsCode;
    }

    public String getGoodName() {
        return GoodName;
    }

    public void setGoodName(String goodName) {
        GoodName = goodName;
    }

    public double getGoodsPrice() {
        return GoodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        GoodsPrice = goodsPrice;
    }

    public int getGoodsNum() {
        return GoodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        GoodsNum = goodsNum;
    }

    @Override
    public String toString() {
        return "编号:" + GoodsId + "\t条形码:" + GoodsCode + "\t商品名称:" + GoodName + "\t价格:" + GoodsPrice + "\t数量:" + GoodsNum ;
    }
}
