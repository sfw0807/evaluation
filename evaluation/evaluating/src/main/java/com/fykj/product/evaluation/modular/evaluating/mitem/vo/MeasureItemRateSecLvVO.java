package com.fykj.product.evaluation.modular.evaluating.mitem.vo;

import java.util.List;

/**
 * Created by liwang on 2017/3/30.
 */
public class MeasureItemRateSecLvVO {
    private MeasureItemCompRate lvlItem;

    private List<MeasureItemCompRate> lv2Items;

    public MeasureItemCompRate getLvlItem() {
        return lvlItem;
    }

    public void setLvlItem(MeasureItemCompRate lvlItem) {
        this.lvlItem = lvlItem;
    }

    public List<MeasureItemCompRate> getLv2Items() {
        return lv2Items;
    }

    public void setLv2Items(List<MeasureItemCompRate> lv2Items) {
        this.lv2Items = lv2Items;
    }
}
