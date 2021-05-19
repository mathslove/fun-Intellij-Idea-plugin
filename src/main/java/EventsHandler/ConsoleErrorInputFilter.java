package EventsHandler;

import com.intellij.execution.filters.InputFilter;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ConsoleErrorInputFilter implements InputFilter {
    private final Project project;
    private String errorStack;

    public ConsoleErrorInputFilter(final Project project) {
        this.project = project;
        errorStack = null;
    }

    @Override
    public @Nullable List<Pair<String, ConsoleViewContentType>> applyFilter(@NotNull String text, @NotNull ConsoleViewContentType contentType) {
        if (contentType.equals(ConsoleViewContentType.ERROR_OUTPUT)) {
            this.errorStack += text;
        }
        if (contentType.equals(ConsoleViewContentType.SYSTEM_OUTPUT) && text.trim().startsWith("Process finished with exit code")) {
            if (errorStack != null) {
                var site = "https://www.google.com/search?q=";
                var search = errorStack.replaceAll("^[( )]*", "+");
                BrowserUtil.browse(site + search);
            }
        }

        return Collections.singletonList(Pair.create(text, contentType));
    }
}
