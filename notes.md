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

https://pt.wikipedia.org/wiki/Programação_funcional
https://developer.android.com/kotlin/get-started#kotlin

https://blog.mindorks.com/a-complete-guide-to-learn-kotlin-for-android-development-b1e5d23cc2d8
https://code.tutsplus.com/tutorials/start-developing-android-apps-with-kotlin-part-1--cms-27827
https://github.com/ItaloBruno/Kotlin/blob/master/src/basic/stringFormat.kt
https://github.com/Webschool-io/Curso-Kotlin/blob/master/aulas/aula_01.md
https://www.androidpro.com.br/blog/kotlin/kotlin/#O_que_e_o_Kotlin
https://coding180.com/kotlin/keyboard-data-entry-on-the-console/
http://kotlinlang.org/docs/reference/basic-syntax.html



-   [Android Developers Blog Announcing Kotlin](https://android-developers.googleblog.com/2017/05/android-announces-support-for-kotlin.html)
-   [Install JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  if you don't have it
-   [Install IntelliJ](https://www.jetbrains.com/help/idea/install-and-set-up-product.html)
----

# Installing the Java Development Kit (JDK)

If you don't have the latest JDK already installed on your computer, follow the steps below. You will need to have the JDK installed to run Kotlin programs.

The JDK is freely available, and you can download it here:  [http://www.oracle.com/technetwork/java/javase/overview/index.html](http://www.oracle.com/technetwork/java/javase/overview/index.html).

## The JDK or the JRE?

The JRE (Java Runtime Environment) is needed for running Java and Kotlin programs. The JDK (Java Development Kit), on the other hand, includes the JRE, plus the development tools you'll need for writing and running Java programs. You need the JDK for writing Kotlin Programs.

## Steps to install the JDK

### 1. Uninstall any older versions of the JDK/JRE

We recommend that you install only the latest JDK.

### 2. Download the JDK

You can download the JDK for free here:  [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

1.  Click the "Download" button under the JDK for the latest Java SE version.
2.  Check "Accept License Agreement".
3.  Choose the JDK for your operating system.

### 3. Install the JDK (for Mac)

From either the Downloads window of the browser, or from the file browser, double-click the .dmg file to launch the install file.

1.  A Finder window appears with an icon of an open box and the name of the .pkg file.
2.  Double-click the package icon to launch the Install app, and follow the prompts as they appear.
3.  You might need to enter the administrator password to continue.
4.  Feel free to delete the .dmg file to save space after the installation is complete.

### 3. Install the JDK (for Windows)

Run the downloaded installer (e.g.,  `jdk-10.0.x_windows-x64_bin.exe`), which installs both the JDK and the JRE.

By default, the JDK will be installed in the directory "C:\Program Files\Java\jdk-10.0.x", where x denotes the version number; and the JRE in "C:\Program Files\Java\jre-10.0.x".

Accept the defaults, and follow the screen instructions to install the JDK.

### 4. Add the JDK installation path to PATH (Windows only)

Windows searches the current directory and the directories listed in the PATH environment variable (system variable) for executable programs.

1.  Open "Control Panel" -> "System" -> "Advanced system settings" -> "Environment Variables".
2.  Under "System variables", scroll down to select "Path" and click "Edit...".
3.  Append to the existing Path value a semi-colon ";" then the JDK's "bin" directory (e.g. ";C:\Program Files\Java\jdk-10.0.0\bin").


-----

### Code to enter into REPL:

```
fun printHello () {
   println ("Hello World")
}

printHello()

```

-   [Kotlin Language Documentation](http://kotlinlang.org/docs/reference/)

http://kotlinlang.org/docs/reference/
https://try.kotlinlang.org/


https://github.com/informramiz/kotlin-bootcamp
https://try.kotlinlang.org/#/Examples/Hello,%20world!/Simplest%20version/Simplest%20version.kt
http://kotlinlang.org/docs/reference/basic-syntax.html
https://classroom.udacity.com/courses/ud9011/lessons/b079d09d-0854-49a3-8318-b8e17513bd36/concepts/94231ece-4860-409a-b700-ed1d44f8ebc1
http://jsbin.com/fohizukine/edit?html
https://www.temporealeventos.com.br/curso-kotlin-presencial
https://kotlinexpertise.com/kotlin-byte-code-generation/
https://kotlinexpertise.com/kotlin-features-miss-java/
https://kotlinexpertise.com/gradlekotlindsl/
https://kotlinexpertise.com/kotlinquickreference/
https://kotlinexpertise.com/android-ktx-kotlin/
https://proandroiddev.com/android-jetpack-navigationui-a7c9f17c510e



---

https://www.raywenderlich.com/165824/introduction-android-activities-kotlin

att.
    
Jackson F. de A. Mafra
about.me/jacksonfdam 
http://linkedin.com/in/jacksonfdam



Expressões lambda são uma funcionalidade comum em muitas linguagens, em particular as que seguem o paradigma Programação Funcional (o termo em si vem do Cálculo Lambda, fundação matemática que sustenta esse paradigma), mas que recentemente vêm sendo introduzidas em linguagens de outros paradigmas (como, no caso, o Imperativo/Orientado por Objetos). Para entendê-las, é necessário conhecer os conceitos de funções de primeira classe e literais.

Tradicionalmente no Java, um método (função, procedimento) somente existe como membro de uma classe. Isso significa que, embora você possa ter uma variável "apontando" para um objeto, você não pode guardar um método numa variável. Tudo aquilo que é permitido referenciar numa linguagem (no caso objetos ou tipos primitivos), passar como parâmetro para outras funções, etc, é dito ser "de primeira classe".


É uma tentativa (mal sucedida na minha opnião) de inserir uma característica funcional na linguagem. Na prática vai evitar que você tenha que criar classes anônimas de apenas um método, pois é isso que expressões lambda fazem. Adição de Listeners, Runnables, Callables e afins irão ficar menos verbosos.

Ao invés de:

new Thread(new Runnble() {
  public void run() {
    System.out.println("Running...");
  }
}).start();
Teremos:

new Thread(() -> System.out.println("Running with lambda")).start();


-----------

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