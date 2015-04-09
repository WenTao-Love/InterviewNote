###Java容器和泛型（3）HashSet、TreeSet和LinkedHashSet比较
####Set回顾

**一个不包括重复元素（包括可变对象）**的Collection，是一种无序的集合。Set不包含满 a.equals(b) 的元素对a和b，并且最多有一个null。

泥瓦匠的记忆宫殿：

1、**不允许包含相同元素**

2、**判断对象是否相同，根据equals方法**

![Set](http://static.codeceo.com/images/2015/04/b4450c48c83c37d33ca062828083a3e9.jpg)

####HashSet

一个按着Hash算法来存储集合中的元素，其元素值可以是NULL。它不能保证元素的排列顺序。同样，HashSet是不同步的，如果需要多线程访问它的话，可以用 Collections.synchronizedSet 方法来包装它：

<code>Set s = Collections.synchronizedSet(new HashSet(...));</code>

同上一节一样，用迭代器的时候，也要注意 并发修改异常ConcurrentModificationException。
要注意的地方是，HashSet集合判断两个元素相等不单单是equals方法，并且必须hashCode()方法返回值也要相等。看下面的例子：

    import java.util.HashSet;

	class EuqalsObj
	{
	    public boolean equals(Object obj)
	    {
	        return true;
	    }
	}
	
	class HashCodeObj
	{
	    public int hashCode()
	    {
	        return 1;
	    }
	}
	
	class HashSetObj
	{
	    public int hashCode()
	    {
	        return 2;
	    }
	
	    public boolean equals(Object obj)
	    {
	        return true;
	    }
	}

	public class HashSetTest
	{
	    public static void main(String[] args)
	    {
	        HashSet objs = new HashSet();
	        objs.add(new EuqalsObj());
	        objs.add(new EuqalsObj());
	        objs.add(new HashCodeObj());
	        objs.add(new HashCodeObj());
	        objs.add(new HashSetObj());
	        objs.add(new HashSetObj());

	        System.out.println("HashSet Elements:");
	        System.out.print("/t" + objs + "/n");
	    }
	}

控制台输出：

    HashSet Elements:
    [HashCodeObj@1, HashCodeObj@1, HashSetObj@2, EuqalsObj@1471cb25, EuqalsObj@3acff49f]

泥瓦匠根据结果，一一到来。首先，排列顺序不定。

HashSetObj 类满足我们刚刚的要求，所以集合中只有一个且它的HashCode值为2。

HashCodeObj 类虽然它们HashCode值为1，但是他们不相等。（其实当HashCode值一样，这个存储位置会采用链式结构保存两个HashCodeObj对象。）

同样,EqualsObj 类他们相等，但是他们HashCode值不等，分别为1471cb25、3acff49f。

因此，用HashSet添加可变对象，要注意当对象有可能修改后和其他对象矛盾，这样我们无法从HashSet找到准确我们需要的对象。

####LinkedHashSet

HashSet的子类，也同样有HashCode值来决定元素位置。但是它使用链表维护元素的次序。记住两个字：有序。

有序的妙用，复制。比如泥瓦匠实现一个HashSet无序添加，然后复制一个一样次序的HashSet来。代码如下：

    package com.sedion.bysocket.collection;

	import java.util.HashSet;
	import java.util.LinkedHashSet;
	import java.util.Set;
	
	public class LinkedHashListTest
	{
	    public static void main(String[] args)
	    {
	        /* 复制HashSet */
	        Set h1 = new HashSet<String>();
	        h1.add("List");
	        h1.add("Queue");
	        h1.add("Set");
	        h1.add("Map");

	        System.out.println("HashSet Elements:");
	        System.out.print("/t" + h1 + "/n");
	
	        Set h2 = copy(h1);
	        System.out.println("HashSet Elements After Copy:");
	        System.out.print("/t" + h2 + "/n");
	    }

	    @SuppressWarnings({ "rawtypes", "unchecked" })
	    public static Set copy(Set set)
	    {
	        Set setCopy = new LinkedHashSet(set);
	        return setCopy;
	    }
	}

控制台输出：

    HashSet Elements:
    [Map, Queue, Set, List]
	HashSet Elements After Copy:
    [Map, Queue, Set, List]

可见，每个数据结构都有它存在的理由。

####TreeSet

TreeSet使用树结构实现（红黑树），集合中的元素进行排序，但是添加、删除和包含的算法复杂度为O（log（n））。

举个例子吧，首先我们定义一个Bird类。（鸟是泥瓦匠最喜欢的动物）

    class Bird
	{
	    int size;
	
	    public Bird(int s)
	    {
	        size = s;
	    }
	
	    public String toString()
	    {
	        return size + "";
	    }

	}

然后用TreeSet添加Bird类。

    public class TreeSetTest
	{	
	    public static void main(String[] args)
	    {
	        TreeSet<Bird> bSet = new TreeSet<Bird>();
	        bSet.add(new Bird(1));
	        bSet.add(new Bird(3));
	        bSet.add(new Bird(2));
	
	        Iterator<Bird> iter = bSet.iterator();
	
	        while (iter.hasNext())
	        {
	            Bird bird = (Bird) iter.next();
	            System.out.println(bird);
	        }
	    }
	}

控制台输出：

    Exception in thread "main" java.lang.ClassCastException: Bird cannot be cast to java.lang.Comparable
    at java.util.TreeMap.compare(Unknown Source)
    at java.util.TreeMap.put(Unknown Source)
    at java.util.TreeSet.add(Unknown Source)
    at com.sedion.bysocket.collection.TreeSetTest.main(TreeSetTest.java:29)

答案很明显，TreeSet是排序的。所以Bird需要实现Comparable此接口。

java.lang.Comparable此接口强行对实现它的每个类的对象进行整体排序。这种排序被称为类的自然排序，类的 compareTo 方法被称为它的自然比较方法。

修改Bird如下：

    class Bird implements Comparable<Bird>
	{
	    int size;
	
	    public Bird(int s)
	    {
	        size = s;
	    }
	
	    public String toString()
	    {
	        return size + "号鸟";
	    }
	
	    @Override
	    public int compareTo(Bird o)
	    {
	        return size - o.size;
	    }

	}

再次输出：

    1号鸟
	2号鸟
	3号鸟

####性能测试比较

针对上面三种Set集合，我们对它们的Add方法进行性能测试：

    import java.util.HashSet;
	import java.util.LinkedHashSet;
	import java.util.Random;
	import java.util.TreeSet;
	
	class Bird implements Comparable<Bird>
	{
	    int size;

	    public Bird(int s)
	    {
	        size = s;
	    }
	
	    public String toString()
	    {
	        return size + "号鸟";
	    }
	
	    @Override
	    public int compareTo(Bird o)
	    {
	        return size - o.size;
	    }

	}
	
	public class Set
	{
	    public static void main(String[] args)
	    {
	        Random r = new Random();

	        HashSet<Bird> hashSet = new HashSet<Bird>();
	        TreeSet<Bird> treeSet = new TreeSet<Bird>();
	        LinkedHashSet<Bird> linkedSet = new LinkedHashSet<Bird>();
	
	        // start time
	        long startTime = System.nanoTime();
	
	        for (int i = 0; i < 1000; i++) {
	            int x = r.nextInt(1000 - 10) + 10;
	            hashSet.add(new Bird(x));
	        }
	        // end time
	        long endTime = System.nanoTime();
	        long duration = endTime - startTime;
	        System.out.println("HashSet: " + duration);
	
	        // start time
	        startTime = System.nanoTime();
	        for (int i = 0; i < 1000; i++) {
	            int x = r.nextInt(1000 - 10) + 10;
	            treeSet.add(new Bird(x));
	        }
	        // end time
	        endTime = System.nanoTime();
	        duration = endTime - startTime;
	        System.out.println("TreeSet: " + duration);
	
	        // start time
	        startTime = System.nanoTime();
	        for (int i = 0; i < 1000; i++) {
	            int x = r.nextInt(1000 - 10) + 10;
	            linkedSet.add(new Bird(x));
	        }
	        // end time
	        endTime = System.nanoTime();
	        duration = endTime - startTime;
	        System.out.println("LinkedHashSet: " + duration);
	    }
	}


控制台输出：

    HashSet: 2610998
	TreeSet: 3195378
	LinkedHashSet: 2673782

可见，TreeSet因为需要进行比较，所以性能比较差。

####总结

**HashSet：equlas hashcode**

**LinkedHashSet：链式结构**

**TreeSet：比较，Comparable接口，性能较差**

