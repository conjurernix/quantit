package adapter.impl;

import adapter.api.SubscriberAdapter;
import bar.Bar;
import clojure.java.api.Clojure;
import clojure.lang.IFn;
import clojure.lang.Keyword;

public abstract class BaseSubscriberAdapter implements SubscriberAdapter {
    static {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("clojure.core.async"));
    }

    private static final IFn pushToChannel = Clojure.var("clojure.core.async", ">!!");

    private String symbol;
    private Object channel;

    public void _run_intern(String symbol, Object channel) {
        this.symbol = symbol;
        this.channel = channel;
        this.run(symbol);
    }

    public void next(Bar bar) {
        pushToChannel.invoke(this.channel, bar.toPersistentArrayMap());
    }

    public void end() {
        pushToChannel.invoke(this.channel, Keyword.find("end"));
    }

    public String getSymbol() {
        return symbol;
    }
}
