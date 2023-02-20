package com;

public class RegularExpression {
    public static void main(String[] args) {
        /*
        d - одна цифра
        + - один и более
        * - 0 и более
        ? - символ, который идет до "?", может быть, а может и не быть
        (x|y|z) - одно из множества
        [a-z] - все букавы конкретного алфавита (в данном случае латинского), но в нижнем регистре
        [A-Z] - все букавы конкретного алфавита (в данном случае латинского), но в верхнем регистре
        [a-zA-Z] - все букавы конкретного алфавита (в данном случае латинского), регистра не важен
        [0-9] - любая цифра
        [^abc] - букв не должно быть (указывать в нужном регистре)
        {2} - два символа
        {2, } - от двух до бесконечности
        {2, 4} - от двух до четрых
        \\w - одна буква ( \\ w = [a-zA-Z])
        \\w{5} - слово из любых ПЯТИ букв
        ^abc - символы должны быть в начале строки (в этой же последовательности)
        abc$ - символы должны быть в конце строки (в этой же последовательности)
        .    - любой один символ
        .+   - любое количество любых символов
        _________
        \\.    экранирование точки


         */


       /* String a = "z234";
        System.out.println(a.matches("\\d"));
        System.out.println(a.matches("-\\d+"));
        System.out.println(a.matches("(x|y|z)\\d+"));
        System.out.println(a.matches("[a-z]\\d+"));
        System.out.println(a.matches("[a-zA-Z]\\d+"));
        System.out.println(a.matches("[a-zA-Z123]\\d+")); // когда мы точно знаем, какие цифры
        System.out.println(a.matches("[a-zA-Z0-9]\\d+")); // когда мы не знаем, какие цифры*/

       /* String c = "helrlo";
        System.out.println(c.matches("[a-zA-Z\\d+]{6,}"));*/

        String url = "https://www.google.ru";
        //  System.out.println(url.matches("https://www\\..+\\.(com|de)"));  // домен должен быть либо
        System.out.println(url.matches("https://www\\..+\\.[a-zA-Z]"));  // домен их любых латинских букв
        System.out.println(url.matches("https://www\\..+\\.[а-я]"));  // домен должен быть из любых кирилических букв

    }

}
