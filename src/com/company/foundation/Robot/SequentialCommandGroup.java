package com.company.foundation.Robot;

import java.util.ArrayList;
import java.util.Collections;

public class SequentialCommandGroup extends CommandBase {

    private ArrayList<CommandBase> commandList = new ArrayList<>();
    private CommandBase activeCommand;
    private int index;

    public SequentialCommandGroup(CommandBase... commands) {
        Collections.addAll(commandList, commands);
    }

    @Override
    public void initialize() {
        for (CommandBase command : commandList) {
            command.doinitialize();
        }
        activeCommand = commandList.get(0);
    }

    @Override
    public void execute() {
        if (activeCommand == null) return;
        activeCommand.doExecute();
        if (activeCommand.getIsFinished()) {
            activeCommand.doEnd();
            try {
                activeCommand = commandList.get(++index);
            } catch (IndexOutOfBoundsException e) {
                activeCommand = null;
            }
        }
    }

    @Override
    public void end() {
    }

    @Override
    public boolean isFinished() {
        return activeCommand == null;
    }
}
