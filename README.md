# Solution Task DeepCopy
## Task:
Бывают в жизни моменты, когда хочется взять и сделать полную копию какого-нибудь объекта.

Как-то так:

```java
ComplexObject obj = ...
ComplexObject copy = CopyUtils.deepCopy(obj);
```

Проблема в том, что классы в Java бывают произвольной сложности - количество полей класса и их типы никак не регламентированы. Более того, система типов в Java замкнута - элементами массива/списка могут являться абсолютно любые типы данных, в том числе и массивы/списки. А ещё рекурсивные структуры данных - когда объект где-то в своих дебрях содержит ссылку на себя самого. Или на свою часть.

Вам необходимо написать метод deepCopy(), который учитывает все эти нюансы и работает на объектах произвольной структуры и размера.

Немного деталей:
- Работать метод должен в первую очередь правильно, а уже потом быстро.
- Использовать можно только возможности стандартной библиотеки J2SE
- Писать нужно на Java (версия 11 и выше) или Kotlin.

Пример класса, на котором можно проверить решение:
```java
class Man {
	private String name;
	private int age;
	private List<String> favoriteBooks;

	public Man(String name, int age, List<String> favoriteBooks) {
		this.name = name;
		this.age = age;
		this.favoriteBooks = favoriteBooks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getFavoriteBooks() {
		return favoriteBooks;
	}

	public void setFavoriteBooks(List<String> favoriteBooks) {
		this.favoriteBooks = favoriteBooks;
	}
	
}
```

