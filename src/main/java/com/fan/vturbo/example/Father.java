package com.fan.vturbo.example;

public class Father {

    public String name = "慕应雄";

    private int age = 40;

    String gender = "man";

    protected int height = 175;

    public void sayName(){
        System.out.println("慕应雄");
        System.out.println(age);
    }

    private void sayAge(){
        System.out.println(40);
    }

    void sayGender(){
        System.out.println("man");
    }

    protected void sayHeight(){
        System.out.println(175);
    }

    public void one(){

    }
    public void one(int a,String b){

    }
    public void one(int a){

    }
    public String one(int a,int b){
        return "";
    }

}
