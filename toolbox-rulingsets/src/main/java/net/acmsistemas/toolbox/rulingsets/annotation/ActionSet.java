/**
 * AMCSistemas toolbox RulingSets
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.acmsistemas.toolbox.rulingsets.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(CLASS)
@Target(TYPE)
/**
 * Classes annotated with {@code @ActionSet} will be loaded to the Action Set list.
 * An {@code @ActionSet} class contains a set of methods that may be used in the
 * action execution step of a Rule.  
 * The {@link #name} attribute is used to identify the action set in the Rule.
 * 
 * @author William Martínez Pomares
 */
public @interface ActionSet {
	String[] name() default {};
}
