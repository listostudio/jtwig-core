package org.jtwig.model.expression.operation.calculators.binary;

import org.jtwig.context.RenderContext;
import org.jtwig.model.expression.Expression;
import org.jtwig.model.expression.operation.binary.calculators.MultiplyOperationCalculator;
import org.jtwig.model.position.Position;
import org.jtwig.value.JtwigValue;
import org.jtwig.value.JtwigValueFactory;
import org.jtwig.value.configuration.DefaultValueConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MultiplyOperationCalculatorTest {
    private final RenderContext renderContext = mock(RenderContext.class, RETURNS_DEEP_STUBS);
    private final Position position = mock(Position.class);
    private MultiplyOperationCalculator underTest = new MultiplyOperationCalculator();

    @Before
    public void setUp() throws Exception {
        when(renderContext.environment().valueConfiguration().getMathContext()).thenReturn(MathContext.DECIMAL32);
        when(renderContext.environment().valueConfiguration()).thenReturn(new DefaultValueConfiguration());
    }

    @Test
    public void multiplyWithFloating() throws Exception {
        Expression leftOperand = mock(Expression.class);
        Expression rightOperand = mock(Expression.class);
        when(leftOperand.calculate(renderContext)).thenReturn(JtwigValueFactory.value(new BigDecimal("1.3"), new DefaultValueConfiguration()));
        when(rightOperand.calculate(renderContext)).thenReturn(JtwigValueFactory.value(new BigDecimal("2.3"), new DefaultValueConfiguration()));

        JtwigValue result = underTest.calculate(renderContext, position, leftOperand, rightOperand);

        assertThat(result.mandatoryNumber(), is(new BigDecimal("2.99")));
    }
}