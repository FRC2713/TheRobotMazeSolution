package com.company.foundation.Robot;

import java.util.ArrayList;
import java.util.Collections;

public class ParallelCommandGroup extends CommandBase {

    private boolean is_finished;
    private ArrayList<CommandBase> commandList = new ArrayList<>();

    public ParallelCommandGroup(CommandBase... commands) {
        Collections.addAll(commandList, commands);
    }

    @Override
    public void initialize() {
        for (CommandBase command : commandList) {
            command.doinitialize();
        }
    }

    @Override
    public void execute() {
        for (CommandBase command : commandList) {
            command.doExecute();
        }
    }

    @Override
    public void end() {
        for (CommandBase command : commandList) {
            command.doEnd();
        }
    }

    @Override
    public boolean isFinished() {
        is_finished = true;
        for (CommandBase command : commandList) {
            if (!command.getIsFinished()) is_finished = false;
        }
        return is_finished;
    }
}
