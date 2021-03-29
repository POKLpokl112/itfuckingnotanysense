package itfuckingnotanysense.onemorething;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import itfuckingnotanysense.onemorething.BigMode.AbstractOp;
import itfuckingnotanysense.onemorething.BigMode.BlackDecorator;
import itfuckingnotanysense.onemorething.BigMode.Bridge;
import itfuckingnotanysense.onemorething.BigMode.Bridge1Impl;
import itfuckingnotanysense.onemorething.BigMode.Bridge2Impl;
import itfuckingnotanysense.onemorething.BigMode.Coffee;
import itfuckingnotanysense.onemorething.BigMode.Composite;
import itfuckingnotanysense.onemorething.BigMode.CompositeImpl;
import itfuckingnotanysense.onemorething.BigMode.CurrentDisplay;
import itfuckingnotanysense.onemorething.BigMode.Decorator;
import itfuckingnotanysense.onemorething.BigMode.Duck;
import itfuckingnotanysense.onemorething.BigMode.Facade;
import itfuckingnotanysense.onemorething.BigMode.FlyWeight;
import itfuckingnotanysense.onemorething.BigMode.FlyWeightFactory;
import itfuckingnotanysense.onemorething.BigMode.HighResolutionImage;
import itfuckingnotanysense.onemorething.BigMode.ITV;
import itfuckingnotanysense.onemorething.BigMode.ImageProxy;
import itfuckingnotanysense.onemorething.BigMode.Leaf;
import itfuckingnotanysense.onemorething.BigMode.OneDecoratorImpl;
import itfuckingnotanysense.onemorething.BigMode.PeopleTalk;
import itfuckingnotanysense.onemorething.BigMode.QuakStrategy;
import itfuckingnotanysense.onemorething.BigMode.See;
import itfuckingnotanysense.onemorething.BigMode.Sony;
import itfuckingnotanysense.onemorething.BigMode.SqueakStrategy;
import itfuckingnotanysense.onemorething.BigMode.StatDisplay;
import itfuckingnotanysense.onemorething.BigMode.StateMachine;
import itfuckingnotanysense.onemorething.BigMode.TalkAdapter;
import itfuckingnotanysense.onemorething.BigMode.Tea;
import itfuckingnotanysense.onemorething.BigMode.Template;
import itfuckingnotanysense.onemorething.BigMode.Tv;
import itfuckingnotanysense.onemorething.BigMode.TwoDecoratorImpl;
import itfuckingnotanysense.onemorething.BigMode.VCusGroup;
import itfuckingnotanysense.onemorething.BigMode.VReport;
import itfuckingnotanysense.onemorething.BigMode.WeatherData;
import itfuckingnotanysense.onemorething.BigMode.WhiteDecorator;
/**
 * 
 * 
 创建型
    // 单例.md
    // 简单工厂.md
    // 工厂方法.md
    // 抽象工厂.md
    // 生成器.md
    // 原型模式.md
 行为型
    // 责任链.md
    // 命令.md
    // 解释器.md
    // 迭代器.md
    // 中介者.md
    // 备忘录.md
    观察者.md
    状态.md
    策略.md
    模板方法.md
    访问者.md
    空对象.md
结构型
    适配器.md
    桥接.md
    组合.md
    装饰.md
    外观.md
    享元.md
    代理.md
 * 
 * 
 * 
 */
public class DesignPattern {

    public static void main(String[] args) {
        // System.out.println("test");
        // testObServer();
        // testState();
        // testStrategy();
        // testTemplate();
       // testNullOp();
       //testAdapter();
       //testBridge();

       //testComposite();
    //    testDeCorator();
        //testFacade();
        // testFlyWeight();

        testProxy();
    }

    public static void testProxy(){
        try {
            ImageProxy.g(HighResolutionImage.g(new URL("http://image.jpg"))).show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void testFlyWeight(){
        FlyWeightFactory factory = new FlyWeightFactory();
        FlyWeight flyWeight=factory.g("aa");
        flyWeight.run("x");
        flyWeight=factory.g("aa");
        flyWeight.run("y");
    }
    
    public static void testFacade(){
        new Facade().facadeRun();
    }

    public static void testDeCorator(){
        Decorator whiteDecorator = new WhiteDecorator();
        Decorator blackDecorator = new BlackDecorator();
        whiteDecorator =OneDecoratorImpl.g(whiteDecorator) ;
        whiteDecorator=TwoDecoratorImpl.g(blackDecorator);
        System.out.println(whiteDecorator.cost());
        blackDecorator=OneDecoratorImpl.g(whiteDecorator);
        blackDecorator=TwoDecoratorImpl.g(blackDecorator);
        System.out.println(blackDecorator.cost());
    }

    public static void testComposite(){
        Composite root = CompositeImpl.g("root");
        Composite leaf1 = Leaf.g("leaf1");
        Composite composite1=CompositeImpl.g("composite1");
        Composite leaf2 = Leaf.g("leaf2");

        root.add(leaf1);
        root.add(composite1);
        root.add(leaf2);

        Composite composite2 = CompositeImpl.g("composite1_1");
        Composite leaf11 = Leaf.g("leaf1_1");
        Composite leaf111=Leaf.g("leaf1_1_1");

        composite1.add(composite2);
        composite1.add(leaf11);
        composite2.add(leaf111);

        root.show(0);
    }

    public static void testBridge(){
        Tv sony = new Sony();
        Tv screen = new ITV();

        Bridge bridge1Bridge = new Bridge1Impl(sony);
        Bridge bridge2Bridge =new Bridge2Impl(sony);
        bridge1Bridge.on();
        bridge1Bridge.off();
        bridge1Bridge.turn();

        bridge2Bridge.on();
        bridge2Bridge.off();
        bridge2Bridge.turn();

        bridge1Bridge.tv=screen;
        bridge2Bridge.tv=screen;
        bridge1Bridge.on();
        bridge1Bridge.off();
        bridge1Bridge.turn();

        bridge2Bridge.on();
        bridge2Bridge.off();
        bridge2Bridge.turn();
    }

    public static void testAdapter(){
        
        See adapter = new TalkAdapter(new PeopleTalk());
        adapter.see();
    }

    public static void testNullOp(){
        AbstractOp op = AbstractOp.gOp(true);
        op.request();
        System.out.println("----below got null op----");
        op=AbstractOp.gOp(false);
        op.request();

    }

    public static void testVisitor() {
        VReport report = new VReport();
        VCusGroup cusGroup = new VCusGroup();
        cusGroup.visit(report);
        report.report();
    }

    public static void testTemplate() {

        Template template = new Tea();
        template.run();
        System.out.println("-----------");
        template = new Coffee();
        template.run();
    }

    public static void testStrategy() {
        Duck duck = new Duck();

        duck.setStrategy(new QuakStrategy());
        duck.show();
        duck.setStrategy(new SqueakStrategy());
        duck.show();
    }

    public static void testState() {
        StateMachine sm = new StateMachine();
        sm.ejectQ();
        sm.run();
        sm.turn();
        sm.insertQ();
        sm.ejectQ();
        sm.turn();
        sm.ejectQ();
        sm.insertQ();
        sm.turn();
        sm.insertQ();
        sm.insertQ();
        sm.turn();
        sm.turn();
        sm.insertQ();
        sm.turn();
        sm.insertQ();
        sm.turn();
        sm.insertQ();
        sm.turn();
        sm.insertQ();
        sm.turn();

    }

    public static void testObServer() {
        WeatherData weatherData = new WeatherData();
        weatherData.regObServer(new StatDisplay());
        weatherData.regObServer(new CurrentDisplay());
        weatherData.setData(1, 1, 1);
        weatherData.setData(2, 2, 2);
    }
}

class BigMode {

    static interface Image{
        void show();
    }

    static class HighResolutionImage implements Image{
        URL url;
        long startTime=System.currentTimeMillis();
        int height = 500,width = 600;

        static HighResolutionImage g(URL url){
            HighResolutionImage image = new HighResolutionImage();
            image.url = url;
            return image;
        }


        boolean isLoad(){
            return System.currentTimeMillis()-startTime>3000;
        }
        @Override
        public void show() {
            System.out.println("real image show:"+url);
        }
    }

    static class ImageProxy implements Image{
        
        HighResolutionImage image;
        static ImageProxy g(HighResolutionImage image){
            ImageProxy imageProxy = new ImageProxy();
            imageProxy.image=image;
            return imageProxy;
        }
        @Override
        public void show() {
            while(!image.isLoad()){
                try {
                    System.out.println("temp image :"+image.height+" "+image.width);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            image.show();
        }
    }

    static interface FlyWeight{
        void run(String someThing);
    }

    static class FlyWeightImpl implements FlyWeight{
        String data;

        static FlyWeight g(String data){
            FlyWeightImpl flyWeightImpl = new FlyWeightImpl();
            flyWeightImpl.data=data;
            return flyWeightImpl;
        }

        @Override
        public void run(String someThing) {
            System.out.println("obj addr : "+hashCode()+"--"+this);
            System.out.println("inner data :"+data);
            System.out.println("outer data:"+someThing);
        }

    }

    static class FlyWeightFactory{
        Map<String,FlyWeight> map = new HashMap<>();
        FlyWeight g(String data){
            FlyWeight flyWeight = map.get(data);
            if(flyWeight==null){
                flyWeight=FlyWeightImpl.g(data);
                map.put(data, flyWeight);
                
            }
            return flyWeight;

        }
    }

    static class FacadeSystem1{
        public void run(){
            System.out.println("facadesystem1 run...");
        }
    }

    static class FacadeSystem2{
        public void run(){
            System.out.println("facadesystem2 run...");
        }
    }

    static class Facade{
        FacadeSystem1 system1 = new FacadeSystem1();
        FacadeSystem2 system2 = new FacadeSystem2();
        public void facadeRun(){
            system1.run();
            system2.run();
            system1.run();

        }
    }

    static interface Decorator{
        int cost();
    }

    static class WhiteDecorator implements Decorator{
        @Override
        public int cost() {
            return 1;
        }
    }

    static class BlackDecorator implements Decorator{
        @Override
        public int cost() {
            return 1;
        }
    }

    static class OneDecoratorImpl implements Decorator{

        Decorator decorato;

        static OneDecoratorImpl g(Decorator decorator){
            OneDecoratorImpl oneDecoratorImpl = new OneDecoratorImpl();
            oneDecoratorImpl.decorato=decorator;
            return oneDecoratorImpl;
        }
        @Override
        public int cost() {
            return 1+decorato.cost();    
        }
    }

    static class TwoDecoratorImpl implements Decorator{
        Decorator decorator;
        static TwoDecoratorImpl g(Decorator decorator){
            TwoDecoratorImpl twoDecoratorImpl = new TwoDecoratorImpl();
            twoDecoratorImpl.decorator=decorator;
            return twoDecoratorImpl;
        }

        @Override
        public int cost() {
            return 1+decorator.cost();
        }
    }

    static interface Composite{
        void show(int i);
        void add(Composite composite);
        void remove(Composite composite);
    }

    static class CompositeImpl implements Composite{
        String name;
        
        List<Composite> list = new ArrayList<>();

        static Composite g(String name){
            CompositeImpl com = new CompositeImpl();
            com.name=name;
            return com;
        }
        @Override
        public void show(int i) {
            for (int j = 0; j < i; j++) {
                System.out.print("--");
            }
            System.out.println("cps impl : "+name);
            for (Composite composite : list) {
                composite.show(i+1);
            }
        }

        @Override
        public void add(Composite composite) {
            list.add(composite);
        }

        @Override
        public void remove(Composite composite) {
            list.remove(composite);
        }
    }

    static class Leaf implements Composite{
        String name;

        static Leaf g(String name){
            Leaf leaf= new Leaf();
            leaf.name=name;
            return leaf;
        }
        @Override
        public void show(int i) {
            for (int j = 0; j < i; j++) {
                System.out.print("--");
            }
            System.out.println("Leaf:"+name);
        }

        @Override
        public void add(Composite composite) {
            
        }

        @Override
        public void remove(Composite composite) {
            
        }
    }

    static interface Tv{
        void on();
        void off();
        void turn();
    }

    static class Sony implements Tv{
        @Override
        public void on() {
            System.out.println("sony on");
        }

        @Override
        public void off() {
            System.out.println("sony off");
        }

        @Override
        public void turn() {
            System.out.println("sony turn");
        }
    }

    static class ITV implements Tv{
        @Override
        public void on() {
            System.out.println("ITV on");
        }

        @Override
        public void off() {
            System.out.println("ITV off");
        }

        @Override
        public void turn() {
            System.out.println("ITV turn");
        }
    }

    static class TvNull implements Tv{
        @Override
        public void on() {
            
        }

        @Override
        public void off() {
            
        }

        @Override
        public void turn() {
            
        }
    }

    static abstract class Bridge{
        Tv tv;
        public Bridge(Tv tv){
            this.tv=tv;
        }
        abstract void on();

        abstract void off();

        abstract void turn();


    }

    static class Bridge1Impl extends Bridge{
        public Bridge1Impl(Tv tv){
            super(tv);
        }
        @Override
        void on() {
            System.out.println("bridge1 on");
            tv.on();
        }

        @Override
        void off() {
            System.out.println("bridge1 off");
            tv.off();
        }

        @Override
        void turn() {
            System.out.println("bridge1 turn");
            tv.turn();
        }
    }

    static class Bridge2Impl extends Bridge{
        public Bridge2Impl(Tv tv){
            super(tv);
        }
        @Override
        void on() {
            System.out.println("bridge2 on");
            tv.on();
        }

        @Override
        void off() {
            System.out.println("bridge2 off");
            tv.off();
        }

        @Override
        void turn() {
            System.out.println("bridge2 turn");
            tv.turn();
        }
    }

    static interface See{
        void see();
    }

    static interface Talk{
        void talk();
    }

    static class PeopleTalk implements Talk{
        @Override
        public void talk() {
            System.out.println("people talk");
        }
    }

    static class TalkAdapter implements See{
        Talk talk;
        public TalkAdapter(Talk talk){
            this.talk=talk;
        }
        @Override
        public void see() {
            talk.talk();
        }
    }

    static abstract class AbstractOp{
        abstract void request();

        static AbstractOp gOp(boolean b){
            if(!b){
                return new NULLOp();
            }

            return new DoOp();
        }
    }

    static class NULLOp extends AbstractOp{
        @Override
        void request() {
            
        }
    }

    static class DoOp extends AbstractOp{
        @Override
        void request() {
            System.out.println("dodododood opopopopopo");
        }
    }

    static class VReport implements Visitor {
        Map<String, Integer> map = new HashMap<>();

        @Override
        public <T extends VElement> void visit(T element) {
            String name = element.getClass().getName();
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        public void report() {
            for (Entry<String, Integer> entry : map.entrySet()) {
                System.out.println("report is name:" + entry.getKey() + ",count:" + entry.getValue());
            }
        }
    }

    static class VCusGroup {
        List<VCustomer> list = new ArrayList<>();
        {
            list.add(VCustomer.get1());
            list.add(VCustomer.get2());
        }

        public void visit(Visitor visitor) {
            for (VCustomer vCustomer : list) {
                vCustomer.accept(visitor);
            }
        }
    }

    static class VCustomer implements VElement {
        String name;
        List<VOrder> orders = new ArrayList<>();

        @Override
        public void accept(Visitor visitor) {
            System.out.println("now visit :" + name);
            visitor.visit(this);
            for (VOrder vOrder : orders) {
                vOrder.accept(visitor);
            }
        }

        public static VCustomer get1() {
            VCustomer customer = new VCustomer();
            customer.name = "customer1";
            customer.orders.add(VOrder.g("order4", VItem.get1()));
            return customer;
        }

        public static VCustomer get2() {
            VCustomer customer = new VCustomer();
            customer.name = "customer2";
            customer.orders = VOrder.get1();
            return customer;
        }

    }

    static class VOrder implements VElement {
        String name;
        List<VItem> items = new ArrayList<>();

        @Override
        public void accept(Visitor visitor) {
            System.out.println("now visit :" + name);
            visitor.visit(this);
            for (VItem vItem : items) {
                vItem.accept(visitor);
                ;
            }
        }

        private static VOrder g(String name, Object item) {
            VOrder order = new VOrder();
            order.name = name;
            if (item != null) {
                if (item instanceof String) {
                    order.items.add(VItem.g((String) item));
                } else {
                    order.items = (List) item;
                }
            }
            return order;
        }

        private static List<VOrder> get1() {
            List<VOrder> list = new ArrayList<>();
            list.add(g("order1", "item4"));
            list.add(g("order2", "item5"));
            list.add(g("order3", "item6"));
            return list;
        }
    }

    static class VItem implements VElement {
        String name;

        @Override
        public void accept(Visitor visitor) {
            System.out.println("now visit :" + name);
            visitor.visit(this);
        }

        private static VItem g(String name) {
            VItem item = new VItem();
            item.name = name;
            return item;
        }

        public static List<VItem> get1() {
            List<VItem> list = new ArrayList<>();
            list.add(g("item1"));
            list.add(g("item2"));
            list.add(g("item3"));
            return list;
        }

    }

    static interface VElement {
        void accept(Visitor visitor);
    }

    static interface Visitor {
        <T extends VElement> void visit(T element);
    }

    static abstract class Template {
        public void run() {
            boilWater();
            addSomething();
            pourInCup();
            taste();
        }

        public void boilWater() {
            System.out.println("boil water");
        }

        public void pourInCup() {
            System.out.println("pour in cup");
        }

        abstract void taste();

        abstract void addSomething();
    }

    static class Tea extends Template {
        @Override
        void taste() {
            System.out.println("sweet teat");
        }

        @Override
        void addSomething() {
            System.out.println("add tea bag");
        }
    }

    static class Coffee extends Template {
        @Override
        void taste() {
            System.out.println("good coffee");
        }

        @Override
        void addSomething() {
            System.out.println("add coffee bean");
        }
    }

    static interface Strategy {
        void behavior();
    }

    static class QuakStrategy implements Strategy {
        @Override
        public void behavior() {
            System.out.println("quak");
        }
    }

    static class SqueakStrategy implements Strategy {
        @Override
        public void behavior() {
            System.out.println("squeak");
        }
    }

    static class Duck {
        Strategy strategy;

        public void setStrategy(Strategy strategy) {
            this.strategy = strategy;
        }

        public void show() {
            if (strategy != null)
                strategy.behavior();
        }
    }

    static interface State {
        void insertQ();

        void ejectQ();

        void turn();

        void run();
    }

    static class Sold implements State {

        private StateMachine stateMachine;

        public Sold(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public void insertQ() {
            System.out.println("sold:insert");

        }

        @Override
        public void ejectQ() {
            System.out.println("sold:eject");

        }

        @Override
        public void turn() {
            System.out.println("sold:turn");

        }

        @Override
        public void run() {
            System.out.println("sold:run");
            stateMachine.releaseData();
            if (stateMachine.data > 0) {
                stateMachine.setState(stateMachine.gState("no"));
            } else {
                stateMachine.setState(stateMachine.gState("out"));
            }
        }

    }

    static class SoldOut implements State {

        private StateMachine stateMachine;

        public SoldOut(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public void insertQ() {
            System.out.println("out:soldout");

        }

        @Override
        public void ejectQ() {
            System.out.println("out:soldout");

        }

        @Override
        public void turn() {
            System.out.println("out:soldout");

        }

        @Override
        public void run() {
            System.out.println("out:soldout");

        }

    }

    static class HasQ implements State {

        private StateMachine stateMachine;

        public HasQ(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public void insertQ() {
            System.out.println("hasQ:you have areald insert a coin");

        }

        @Override
        public void ejectQ() {
            System.out.println("hasQ:eject a coin");
            stateMachine.setState(stateMachine.gState("no"));

        }

        @Override
        public void turn() {
            System.out.println("HasQ:turn...");
            stateMachine.setState(stateMachine.gState("sold"));

        }

        @Override
        public void run() {
            System.out.println("hasQ:you should turn");
        }

    }

    static class NoQ implements State {

        private StateMachine stateMachine;

        public NoQ(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
        }

        @Override
        public void insertQ() {
            System.out.println("Noq:insertQ");
            stateMachine.setState(stateMachine.gState("has"));
        }

        @Override
        public void ejectQ() {
            System.out.println("Noq:You haven't insert a quarter");

        }

        @Override
        public void turn() {
            System.out.println("Noq:You haven't insert a quarter");

        }

        @Override
        public void run() {
            System.out.println("Noq:You haven't insert a quarter");
        }

    }

    static class StateMachine implements State {
        Map<String, State> map = new HashMap<>();

        private int data = 5;
        private State state = gState("no");

        public State gState(String stateKey) {
            State state = null;
            switch (stateKey) {
            case "no": {
                state = map.get(stateKey);
                if (state == null) {
                    state = new NoQ(this);
                    map.put(stateKey, state);
                }
                ;
            }
            case "has": {
                state = map.get(stateKey);
                if (state == null) {
                    state = new HasQ(this);
                    map.put(stateKey, state);
                }
                ;
            }
            case "sold": {
                state = map.get(stateKey);
                if (state == null) {
                    state = new Sold(this);
                    map.put(stateKey, state);
                }
                ;
            }
            case "out": {
                state = map.get(stateKey);
                if (state == null) {
                    state = new SoldOut(this);
                    map.put(stateKey, state);
                }
                ;
            }
            default:
                ;

            }

            return state;
        }

        public void releaseData() {
            System.out.println("releasing ...");
            if (data > 0) {
                data--;
                System.out.println("release data -1,now data is " + data);
            }
        }

        public void setState(State state) {
            this.state = state;
        }

        public void setData(int data) {
            this.data = data;
        }

        @Override
        public void insertQ() {
            state.insertQ();

        }

        @Override
        public void ejectQ() {
            state.ejectQ();

        }

        @Override
        public void turn() {
            state.turn();
            state.run();

        }

        @Override
        public void run() {
            System.out.println("machine dont need this");
            ;
        }

    }

    static interface ObServer {
        void update(int temp, int humidity, float pressure);
    }

    static interface Subject {
        void regObServer(ObServer obServer);

        void rmObServer(ObServer obServer);

        void notifyObServer();
    }

    static class WeatherData implements Subject {

        private List<ObServer> obs = new ArrayList<>();
        private int temp, humidity, pressure;

        public void setData(int temp, int humidity, int pressure) {
            this.temp = temp;
            this.humidity = humidity;
            this.pressure = pressure;
            notifyObServer();
        }

        @Override
        public void regObServer(ObServer obServer) {
            if (obs.contains(obServer)) {
                return;
            }
            obs.add(obServer);
        }

        @Override
        public void rmObServer(ObServer obServer) {
            obs.remove(obServer);
        }

        @Override
        public void notifyObServer() {
            for (ObServer obServer : obs) {
                obServer.update(temp, humidity, pressure);
            }
        }

    }

    static class StatDisplay implements ObServer {

        @Override
        public void update(int temp, int humidity, float pressure) {
            System.out.println("StatDisplay go----temp:" + temp + ",humidity:" + humidity + ",pressure:" + pressure);
        }

    }

    static class CurrentDisplay implements ObServer {

        @Override
        public void update(int temp, int humidity, float pressure) {
            System.out.println("CurrentDisplay go----temp:" + temp + ",humidity:" + humidity + ",pressure:" + pressure);
        }

    }
}
