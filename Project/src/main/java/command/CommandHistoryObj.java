package command;
import java.util.EmptyStackException;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  RerunnableCommand _undoCmd = new RerunnableCommand() {
      public boolean run () {
        boolean result = !_undoStack.empty();
        try {
        	if (result) {
            _redoStack.push(_undoStack.pop()).undo();
            return true;
            }
        }
        catch(EmptyStackException e) {};
        return result;
      }
    };
  RerunnableCommand _redoCmd = new RerunnableCommand() {
      public boolean run () {
        boolean result = !_redoStack.empty();
        try {
        	if (result) {
            _undoStack.push(_redoStack.pop()).redo();
            return true;
        	}
        }
        catch(EmptyStackException e) {};
        return result;
      }
    };

    /**
     * Add command <code>undoable</code> and clear <code>redoable</code>.
     * @param cmd the command to be run.
     */
  public void add(UndoableCommand cmd) {
    
	  _undoStack.add(cmd);
	  _redoStack.clear();
  }
  
  /**
   * Returns a <code>RerunnableCommand</code> that, when run does the following:
   * Pop command from <code>undoable</code>, undo it, then push it
   * onto <code>redoable</code>.
   * @throws EmptyStackException if there is no undoable command.
   * 
   * @return RerunnableCommand inventory after undo
   */
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  /**
   * Returns a <code>RerunnableCommand</code> that, when run does the following:
   * Pop command from <code>redoable</code>, redo it, then push it
   * onto <code>undoable</code>.
   * @throws EmptyStackException if there is no redoable command.
   * 
   * @return RerunnableCommand inventory after redo
   */
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  public UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  public UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
