package restx.jackson;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * User: xavierhanin
 * Date: 2/2/13
 * Time: 5:49 PM
 */
@Retention(RUNTIME)
@JacksonAnnotationsInside

@JsonSerialize(using = FixedPrecisionSerializer.class)
@JsonDeserialize(using = FixedPrecisionDeserializer.class)
public @interface FixedPrecision {
    int value();
}
