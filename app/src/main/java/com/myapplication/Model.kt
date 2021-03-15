package com.myapplication


class Model {
    var name: String
    var price: Double
    var image: String
/*
 *constructeur class Model
 *@param {String} name
 *@param {Double} price
 *@param {String} image
 */
    constructor(name: String,price:Double, image:String){
        this.name = name
        this.price = price
        this.image = image
    }
}