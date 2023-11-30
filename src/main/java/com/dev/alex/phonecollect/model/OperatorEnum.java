package com.dev.alex.phonecollect.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperatorEnum {
    BEELINE("1", "Beeline", "https://kazan.beeline.ru/fancynumber/all/"),
  //  TELE_2("2", "Tele2", "https://msk.tele2.ru/api/shop/products/numbers/groups?indexSeed=2424&siteId=siteMSK"),
    MEGAFON("3", "Megafon", "https://api.shop.megafon.ru/number/116/allNumbers?offset=0&limit=44"),
    MTS("4", "MTS", "https://tatarstan.mts.ru/json/numberselection/getfreephones?inputMask=&msisdnCount=100&lowPrice=0&topPrice=26000");
    //  для категорий цен https://tatarstan.mts.ru/json/numberselection/getCategoryPrices

    private final String code;
    private final String name;
    private final String url;

    @Override
    public String toString() {
        return getName() + " " + getUrl();
    }
}
