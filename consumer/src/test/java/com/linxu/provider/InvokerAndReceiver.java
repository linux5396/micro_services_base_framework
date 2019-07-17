package com.linxu.provider;

/**
 * @author linxu
 * @date 2019/7/15
 */
public class InvokerAndReceiver {
    /**
     * 命令的抽象
     */
    interface Command {
        void execute();
    }

    /**
     * 调用者
     */
    static class Invoker {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void execute() {
            command.execute();
        }
    }

    /**
     * 业务的具体实现
     */
    static class Receiver {
        public void action() {
            //do some task.
            System.out.println("doing...");
        }
    }

    /**
     * 命令的封装
     */
    static class CommandImpl implements Command {
        private Receiver receiver;
        public CommandImpl(Receiver receiver) {
            this.receiver = receiver;
        }
        @Override
        public void execute() {
            receiver.action();
        }
    }

    public static void main(String[] args) {
        Receiver receiver=new Receiver();
        Command command=new CommandImpl(receiver);
        Invoker invoker=new Invoker();
        invoker.setCommand(command);
        invoker.execute();
    }
}
