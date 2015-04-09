###Java容器和泛型（1）认识容器
容器是Java语言学习中重要的一部分。泥瓦匠我的感觉是刚开始挺难学的，但等你熟悉它，接触多了，也就“顺理成章”地知道了。Java的容器类主要由两个接口派生而出：Collection和Map。

####Collection vs Collections
首先，Collection 和 Collections 是两个不同的概念。之所以放在一起，是为了更好的比较。Collection是容器层次结构中根接口。而Collections是一个提供一些处理容器类静态方法的类。

![Collection](http://static.codeceo.com/images/2015/04/8995edf26e2380010abb7c5607eac10c.jpg)

JDK不提供Collection接口的具体实现，而是提供了更加具体的子接口（如Set和List）实现。

那Collection接口存在有何作用呢？存在即是道理。

原因在于：所有容器的实现类（如ArrayList实现了List接口，HashSet实现了Set接口）提供了两个‘标准’的构造函数来实现：1、一个无参的构造方法（void）2、一个带有Collection类型单参数构造方法，用于创建一个具有其参数相同元素新的Collection及其实现类等。实际上：因为所有通用的容器类遵从Collection接口，用第二种构造方法是允许容器之间相互的复制。

####Collection的类层次结构

下面的图是关于Collection的类的层次结构。

![Collection](http://static.codeceo.com/images/2015/04/52d9d88cacd8f073d776c58f7e811a73.jpg)

#####Set

一个不包括重复元素（包括可变对象）的Collection，是一种无序的集合。Set不包含满 a.equals(b) 的元素对a和b，并且最多有一个null。实现Set的接口有：EnumSet、HashSet、TreeSet等。下图是Set的JDK源码UML图。

![Set](http://static.codeceo.com/images/2015/04/8e65080acb274cea5abbe74dcafa8943.jpg)

#####List

一个有序的Collection（也称序列），元素可以重复。确切的讲，列表通常允许满足 e1.equals(e2) 的元素对 e1 和 e2，并且如果列表本身允许 null 元素的话，通常它们允许多个 null 元素。实现List的有：ArrayList、LinkedList、Vector、Stack等。下图是List的JDK源码UML图。

![List](http://static.codeceo.com/images/2015/04/e6c4dbefc0102214a65e8055dc50e8c2.jpg)

#####Queue

一种队列则是双端队列，支持在头、尾两端插入和移除元素，主要包括：ArrayDeque、LinkedBlockingDeque、LinkedList。另一种是阻塞式队列，队列满了以后再插入元素则会抛出异常，主要包括ArrayBlockQueue、PriorityBlockingQueue、LinkedBlockingQueue。虽然接口并未定义阻塞方法，但是实现类扩展了此接口。下图是Queue的JDK源码UML图

![Queue](http://static.codeceo.com/images/2015/04/d49d77d1e90415fee918e760daa8c504.jpg)

####Map的类层次结构

下面的图是Map的层次结构图

![Map](http://static.codeceo.com/images/2015/04/9075f432099db2ad10b410fe5bc10f50.jpg)

#####Map

是一个键值对的集合。也就是说，一个映射不能包含重复的键，每个键最多映射到一个值。该接口取代了Dictionary抽象类。实现map的有：HashMap、TreeMap、HashTable、Properties、EnumMap。下图是Map的JDK源码UML图。

![Map](http://static.codeceo.com/images/2015/04/806230377cd180473c3f64a3420fa61d.jpg)

####容器接口小结

![Collection_interface](http://static.codeceo.com/images/2015/04/collection-summary.png)

####示例代码

    import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.Map;
	import java.util.Set;
	import java.util.TreeMap;
	import java.util.TreeSet;
	
	@SuppressWarnings("unchecked")
	public class CollectionAll
	{

    public static void main(String[] args)
    {
        printLists();

        printSets();

        printMaps();
    }

    private static void printLists()
    {
        List<String> a1 = new ArrayList<String>();
        a1.add("List");
        a1.add("Set");
        a1.add("Queue");
        a1.add("Map");
        System.out.println("ArrayList Elements:");
        System.out.print("/t" + a1 + "/n");

        List<String> l1 = new LinkedList<String>();
        l1.add("List");
        l1.add("Set");
        l1.add("Queue");
        l1.add("Map");
        System.out.println("LinkedList Elements:");
        System.out.print("/t" + l1 + "/n");
    }
    @SuppressWarnings("rawtypes")
    private static void printSets()
    {
        Set h1 = new HashSet<String>();
        h1.add("List");
        h1.add("Set");
        h1.add("Queue");
        h1.add("Map");
        System.out.println("HashSet Elements:");
        System.out.print("/t" + h1 + "/n");

        Set t1 = new TreeSet<String>();
        t1.add("List");
        t1.add("Set");
        t1.add("Queue");
        t1.add("Map");
        System.out.println("TreeSet Elements:");
        System.out.print("/t" + t1 + "/n");
    }

    private static void printMaps()
    {
        Map<String, String> h1 = new HashMap<String, String>();
        h1.put("List", "ArrayList");
        h1.put("Set", "HashSet");
        h1.put("Queue", "PriorityQueue");
        h1.put("Map", "HashMap");
        System.out.println("HashMap Elements:");
        System.out.print("/t" + h1 + "/n");

        Map<String, String> t1 = new TreeMap<String,String>();
        t1.put("List", "ArrayList");
        t1.put("Set", "HashSet");
        t1.put("Queue", "PriorityQueue");
        t1.put("Map", "HashMap");
        System.out.println("TreeMap Elements:");
        System.out.print("/t" + t1 + "/n");

	    }
	}

输出

    ArrayList Elements:
    [List, Set, Queue, Map]
	LinkedList Elements:
	[List, Set, Queue, Map]
	HashSet Elements:
	    [Map, Queue, Set, List]
	TreeSet Elements:
	    [List, Map, Queue, Set]
	HashMap Elements:
	    {Map=HashMap, Queue=PriorityQueue, Set=HashSet, List=ArrayList}
	TreeMap Elements:
	    {List=ArrayList, Map=HashMap, Queue=PriorityQueue, Set=HashSet}

####总结

**Vector和ArrayList**

1，vector是线程同步的，所以它也是线程安全的，而arraylist是线程异步的，是不安全的。如果不考虑到线程的安全因素，一般用arraylist效率比较高。

2，如果集合中的元素的数目大于目前集合数组的长度时，vector增长率为目前数组长度的100%,而arraylist增长率为目前数组长度的50%.如过在集合中使用数据量比较大的数据，用vector有一定的优势。

3，如果查找一个指定位置的数据，vector和arraylist使用的时间是相同的，都是0(1),这个时候使用vector和arraylist都可以。而如果移动一个指定位置的数据花费的时间为0(n-i)n为总长度，这个时候就应该考虑到使用linklist,因为它移动一个指定位置的数据所花费的时间为0(1),而查询一个指定位置的数据时花费的时间为0(i)。

ArrayList 和Vector是采用数组方式存储数据，此数组元素数大于实际存储的数据以便增加和插入元素，都允许直接序号索引元素，但是插入数据要设计到数组元素移动等内存操作，所以索引数据快插入数据慢，Vector由于使用了synchronized方法（线程安全）所以性能上比ArrayList要差，LinkedList使用双向链表实现存储，按序号索引数据需要进行向前或向后遍历，但是插入数据时只需要记录本项的前后项即可，所以插入数度较快！

**Aarraylist和Linkedlist**

1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。

2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。

3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。

这一点要看实际情况的。若只对单条数据插入或删除，ArrayList的速度反而优于LinkedList。但若是批量随机的插入删除数据，LinkedList的速度大大优于ArrayList. 因为ArrayList每插入一条数据，要移动插入点及之后的所有数据。

**HashMap与TreeMap**

1、HashMap通过hashcode对其内容进行快速查找，而TreeMap中所有的元素都保持着某种固定的顺序，如果你需要得到一个有序的结果你就应该使用TreeMap（HashMap中元素的排列顺序是不固定的）。HashMap中元素的排列顺序是不固定的）。

2、  HashMap通过hashcode对其内容进行快速查找，而TreeMap中所有的元素都保持着某种固定的顺序，如果你需要得到一个有序的结果你就应该使用TreeMap（HashMap中元素的排列顺序是不固定的）。集合框架”提供两种常规的Map实现：HashMap和TreeMap (TreeMap实现SortedMap接口)。

3、在Map 中插入、删除和定位元素，HashMap 是最好的选择。但如果您要按自然顺序或自定义顺序遍历键，那么TreeMap会更好。使用HashMap要求添加的键类明确定义了hashCode()和 equals()的实现。 这个TreeMap没有调优选项，因为该树总处于平衡状态。

**hashtable与hashmap**

1、历史原因:Hashtable是基于陈旧的Dictionary类的，HashMap是Java 1.2引进的Map接口的一个实现 。

2、同步性:Hashtable是线程安全的，也就是说是同步的，而HashMap是线程序不安全的，不是同步的 。

3、值：只有HashMap可以让你将空值作为一个表的条目的key或value 。

![Summary](http://static.codeceo.com/images/2015/04/5d24056ca1e571809f0466aa3e831ebb.png)

