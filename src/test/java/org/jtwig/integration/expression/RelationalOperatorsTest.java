package org.jtwig.integration.expression;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class RelationalOperatorsTest {
    @Test
    public void diffOperatorWhenTrue() throws Exception {
        JtwigModel model = new JtwigModel();

        String result = JtwigTemplate
                .inlineTemplate("{% if ('one' != 'two') %}Hi{% endif %}")
                .render(model);

        assertThat(result, is(equalTo("Hi")));
    }

    @Test
    public void diffOperatorWhenFalse() throws Exception {
        JtwigModel model = new JtwigModel();

        String result = JtwigTemplate
                .inlineTemplate("{% if ('one' != 'one') %}Hi{% endif %}")
                .render(model);

        assertThat(result, is(equalTo("")));
    }

    @Test
    public void differentTypesWithSameStringValueAreEqual() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", 5L).with("var2", "5");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 == var2 }}")
                .render(model);

        assertThat(result, is(equalTo("true")));
    }

    @Test
    public void differentTypesWithSameStringValueAreEqual2() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", 'a').with("var2", "a");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 == var2 }}")
                .render(model);

        assertThat(result, is(equalTo("true")));
    }

    @Test
    public void lessTrue() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", 'a').with("var2", "b");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 < var2 }}")
                .render(model);

        assertThat(result, is(equalTo("true")));
    }

    @Test
    public void lessFalse() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", "b").with("var2", "a");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 < var2 }}")
                .render(model);

        assertThat(result, is(equalTo("false")));
    }
    @Test
    public void greaterTrue() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", 'a').with("var2", "b");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 > var2 }}")
                .render(model);

        assertThat(result, is(equalTo("false")));
    }

    @Test
    public void greaterFalse() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", "b").with("var2", "a");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 > var2 }}")
                .render(model);

        assertThat(result, is(equalTo("true")));
    }

    @Test
    public void lessOrEqualTrue() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", 'a').with("var2", "b");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 <= var2 }}")
                .render(model);

        assertThat(result, is(equalTo("true")));
    }

    @Test
    public void lessOrEqualFalse() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", "b").with("var2", "a");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 <= var2 }}")
                .render(model);

        assertThat(result, is(equalTo("false")));
    }
    @Test
    public void greaterOrEqualTrue() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", 'a').with("var2", "b");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 >= var2 }}")
                .render(model);

        assertThat(result, is(equalTo("false")));
    }

    @Test
    public void greaterOrEqualFalse() throws Exception {
        JtwigModel model = new JtwigModel();
        model.with("var1", "b").with("var2", "a");

        String result = JtwigTemplate
                .inlineTemplate("{{ var1 >= var2 }}")
                .render(model);

        assertThat(result, is(equalTo("true")));
    }
}
