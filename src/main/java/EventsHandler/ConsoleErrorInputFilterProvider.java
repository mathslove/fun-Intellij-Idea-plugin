package EventsHandler;


import com.intellij.execution.filters.ConsoleInputFilterProvider;
import com.intellij.execution.filters.InputFilter;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ConsoleErrorInputFilterProvider implements ConsoleInputFilterProvider {
    @NotNull
    @Override
    public InputFilter @NotNull [] getDefaultFilters(@NotNull Project project) {
        InputFilter consoleErrorFilter = new ConsoleErrorInputFilter(project);
        return new InputFilter[]{consoleErrorFilter};
    }
}