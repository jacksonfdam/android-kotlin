# Notas
https://www.androidpro.com.br/blog/kotlin/kotlin/
https://www.tutorialkart.com/kotlin-tutorial/
http://blog.teamtreehouse.com/absolute-beginners-guide-kotlin
https://www.programiz.com/kotlin-programming
https://hackr.io/tutorials/learn-kotlin
https://www.tutorialspoint.com/kotlin/index.htm
https://kotlinlang.org/docs/tutorials/kotlin-android.html
https://kotlinlang.org/docs/books.html
https://br.udacity.com/blog/post/cursos-gratuitos-google-udacity
https://medium.com/@thoughtworksbr/kotlin-explorando-uma-nova-linguagem-️-9c81ced51589

https://github.com/ItaloBruno/Kotlin/blob/master/src/basic/variables.kt
https://www.rafaeltoledo.net/desenvolvendo-para-android-com-kotlin-como-comecar/
https://www.thiengo.com.br/kotlin-android-entendendo-e-primeiro-projeto


### Algum aplicativo Android já utiliza essa nova linguagem em produção?

Provavelmente alguns milhares, mas os maiores que a utilizam, e que o uso da linguagem foi publicado, são:

-   [Pinterest](https://www.youtube.com/watch?v=mDpnc45WwlI "Droidcon NYC 2016 - Kotlin in Production"), com aproximadamente 150 milhões de usuários mensais;
-   [Basecamp 3](https://m.signalvnoise.com/how-we-made-basecamp-3s-android-app-100-kotlin-35e4e1c0ef12 "How we made Basecamp 3’s Android app 100% Kotlin"), alias esse utiliza o Kotlin em 100% do projeto;
-   [Keepsafe](https://medium.com/keepsafe-engineering/lessons-from-converting-an-app-to-100-kotlin-68984a05dcb6 "Lessons from converting an app to 100% Kotlin"), como Basecamp, também com 100% do código em Kotlin.

Há informes de que algumas empresas de softwares bancários já utilizam o Kotlin em suas bases de código, mas nada ainda divulgado na página oficial da linguagem.

Vale ressaltar que o Kotlin adiciona somente algumas centenas de métodos ao projeto Android e um pouco menos do que 100 KB ao APK final. Com o  [Proguard](https://www.thiengo.com.br/proguard-android "Proguard Android") em uso é possível diminuir esses números ainda mais.

### Exemplo de código

Primeiro, saiba que não é meu objetivo aqui trazer a ti toda a documentação do Kotlin, essa já existe e, apesar de estar em inglês, é pequena e fácil de compreender, pois há muito código simples de exemplo.

O que vou fazer é mostrar a linguagem em uma aplicação real, simples, mas com funcionalidade que permita simular uma em produção.

Porém antes de irmos ao projeto de exemplo do artigo, a seguir apresento um código de uma classe Java que estaria sendo utilizada junto a API Gson, ou seja, esta classe tem de ter os métodos getters e setters de cada variável de instância:

```java
public class Carro {
    private String modelo;
    private String marca;
    private int ano;
    private double preco;
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
```

A seguir o código Kotlin da mesma classe, que também poderá ser utilizada junto a API Gson:

```kotlin
class Carro(
        var modelo: String,
        var marca: String,
        var ano: Int,
        var preco: Double )
```

_Oh Yeah!_  O que temos acima é a definição da classe  **Carro**  com um construtor primário. Os métodos getters e setters, acredite, estão ali, são incluídos de forma implícita.

O  **var**  sendo utilizado nos garante esses métodos, pois o  **var**  indica: propriedade mutável. A outra opção é  **val**, que indica: propriedade somente de leitura, não mutável.

_E as chaves de abertura e fechamento de bloco de classe?_

Nem essas são necessárias caso não tenhamos nada de personalizado no corpo da classe.

Note que no Kotlin tudo é objeto, logo, não temos os comuns valores primitivos, tipos básicos, presentes no Java, aqui, apesar do modelo de uso literal de valores do Java ser suportado no Kotlin, podemos invocar métodos e outras propriedades caso existam. O código a seguir é perfeitamente válido:

```kotlin
...1.toString();...
```

Os arquivos de classe Kotlin terminam com  **.kt**.

Com isso podemos partir para nosso projeto Java de exemplo e logo depois para os códigos Kotlin dele.