# Firefly - объектно-ориентированный язык программирования высокого уровня с сиподобным синтаксисом. Является статически типизированным.
# Пример кода на Firefly
```Firefly
class Point{
    public int x, y {get; private set}
    
    public fn Point(int x -> this.x, int Y -> y){
        /* var1 -> var2 позволяет присваивать переменной 
        var2 значение var 1 прямо в аргументах функции */
        
        if(x < 0 || y < 0){
            throw Exception("Ожидаются неотрицательные x и y")
        }
    }
}

fn pl(string a){
    /*Если функция ничего не возвращает, то можно 
    либо написать fn foo() -> void{}, либо просто fn foo(){}*/
    
    println(a)
}

fn getDistanse(Point p1, Point p2) -> double{
    return Math.sqrt((p2.x - p1.x)^2 + (p2.y - p1.y)^2)
}

fn main(){
    //Точка входа в программу, отсюда начинается выполнение
    
    getDistance(Point(2, 3), Point(5, 31)) |> Math.floor() |> pl();
    //Тоже самое что pl(floor(getDistance(Point(2, 3), Point(5, 31))))
}
```