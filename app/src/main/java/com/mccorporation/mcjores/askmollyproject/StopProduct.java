package com.mccorporation.mcjores.askmollyproject;

//{"Глютен", "Клуюника", "Черный перец", "Яблоки", "Орехи", "Рыба", "Моллюски", "Арахис", "Сахар", "Лактоза", "Цитрус", "Яйца", "Соя", "Глюкоза"};
public class StopProduct {

    private boolean gluten;
    private boolean strawberry;
    private boolean blackPaper;
    private boolean apple;
    private boolean nut;
    private boolean fish;
    private boolean mollusk;
    private boolean peanut;
    private boolean sugar;
    private boolean lactose;
    private boolean citrus;
    private boolean egg;
    private boolean soy;
    private boolean glucose;

    public StopProduct() {
        this.gluten = false;
        this.strawberry = false;
        this.blackPaper = false;
        this.apple = false;
        this.nut = false;
        this.fish = false;
        this.mollusk = false;
        this.peanut = false;
        this.sugar = false;
        this.lactose = false;
        this.citrus = false;
        this.egg = false;
        this.soy = false;
        this.glucose = false;
    }

    //Подумать над реализацией сортировки по стоп продуктам
    // Как вариант импользовать Map (key - название продукта-строка value - соответствующее булево значение)
    //Проблема - как будет реализоавн Бэк? как автоматизировать добавлеие продукта в бэк, не исправляя код APP и при этом сохранить сортировку
//    как вриант чисто по слову(строке) т.к. в базе пудет массив с ограничениями
//    Проблема - стоп продукт может содержаться в нескольких продуктах - глютен - овес, макароны, ячмень, блинчики, вафли, колбасы
//    как вариант на каждый стоп продукт создать отдельный класси в нем указывать продукты в которых он содержится

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public void setStrawberry(boolean strawberry) {
        this.strawberry = strawberry;
    }

    public void setBlackPaper(boolean blackPaper) {
        this.blackPaper = blackPaper;
    }

    public void setApple(boolean apple) {
        this.apple = apple;
    }

    public void setNut(boolean nut) {
        this.nut = nut;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
    }

    public void setMollusk(boolean mollusk) {
        this.mollusk = mollusk;
    }

    public void setPeanut(boolean peanut) {
        this.peanut = peanut;
    }

    public void setSugar(boolean sugar) {
        this.sugar = sugar;
    }

    public void setLactose(boolean lactose) {
        this.lactose = lactose;
    }

    public void setCitrus(boolean citrus) {
        this.citrus = citrus;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public void setGlucose(boolean glucose) {
        this.glucose = glucose;
    }

    public boolean isGluten() {
        return gluten;
    }

    public boolean isStrawberry() {
        return strawberry;
    }

    public boolean isBlackPaper() {
        return blackPaper;
    }

    public boolean isApple() {
        return apple;
    }

    public boolean isNut() {
        return nut;
    }

    public boolean isFish() {
        return fish;
    }

    public boolean isMollusk() {
        return mollusk;
    }

    public boolean isPeanut() {
        return peanut;
    }

    public boolean isSugar() {
        return sugar;
    }

    public boolean isLactose() {
        return lactose;
    }

    public boolean isCitrus() {
        return citrus;
    }

    public boolean isEgg() {
        return egg;
    }

    public boolean isSoy() {
        return soy;
    }

    public boolean isGlucose() {
        return glucose;
    }
}
