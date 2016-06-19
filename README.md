# snowfrost

A Clojure client to test the Snowflake Clojure implementation listed here:
https://github.com/pek-github/snowflake
Read there about Snowflake, and its creators "Twitter, Inc.".


## Usage

Run using:
  lein run <SERVICE URL> <NUMBER OF CLIENT THREADS> <NUMBER OF REQUESTS PER THREAD>

Example:
  lein run http://localhost:4444/ 16 100

You can also test for uniqueness via:
  lein run http://localhost:4444/ 16 100 | awk '{ print $7 }' | sort -u | wc -l 
and by expecting that the output will be equal to the product of 16 * 100 

## License

Copyright © 2016 pek

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
