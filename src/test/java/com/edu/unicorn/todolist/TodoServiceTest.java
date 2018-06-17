package com.edu.unicorn.todolist;

import com.edu.unicorn.todolist.app.config.*;
import com.edu.unicorn.todolist.service.*;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceUnitConfig.class, TestPropertiesConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TodoServiceTest {

    private static final String DATABASE_SETUP_CLASSPATH = "classpath:todo-test-entries.xml";

    @Autowired
    private TodoService todoService;

    @Test
    @DatabaseSetup(DATABASE_SETUP_CLASSPATH)
    public void testGetAllCompletedTrue() {
        assertThat(todoService.getAll(ListType.COMPLETED).size())
                .isEqualTo(1);
    }

    @Test
    @DatabaseSetup(DATABASE_SETUP_CLASSPATH)
    public void testGetAllCompletedFalse() {
        assertThat(todoService.getAll(ListType.ACTIVE).size())
                .isEqualTo(1);
    }

    @Test
    @DatabaseSetup(DATABASE_SETUP_CLASSPATH)
    public void testDeleteAllByCompletedTrue() {
        todoService.clearCompleted();
        assertThat(todoService.getAll(ListType.COMPLETED).size())
                .isEqualTo(0);
    }
}
