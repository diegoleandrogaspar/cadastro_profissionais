package br.com.diegoleandro.api.assembler;

import java.util.List;

public interface Converter<T, S, U> {

    public T toDomainObject(U input);

    public S toDto(T domain);

    public List<S> toCollectionDTO(List<T> list);


}
