export interface MoviePageInterface {
  content: [{
    idmovie:  number;
    title:  string;
    year:  number;
    released:  Date;
    runtime:  string;
    genre:  string;
    director:  string;
    writer:  string;
    actors:  string;
    plot:  string;
    language:  string;
    country:  string;
    adwards?:  string;
    poster?:  string;
    dvd:  Date;
    production:  string;
    addFavouritesObj:  Array<Object>;
    ratingsObj:  Array<Object>;
  }];
  pageable?:  {
    sort:  {
      sorted:  boolean;
        unsorted:  boolean;
        empty:  boolean;
    };
    offset:  number;
    pageNumber:  number;
    pageSize:  number;
    unpaged:  boolean;
    paged:  boolean;
  };
  totalPages?:  number;
  totalElements?:  number;
  last?:  boolean;
  size?:  number;
  number?:  number;
  numberOfElements?:  number;
  first?:  boolean;
  sort?:  {
  sorted?:  boolean;
    unsorted:  boolean;
    empty:  boolean;
  };
  empty?:  boolean;
}
